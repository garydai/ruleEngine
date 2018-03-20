<template>
  <div class="dashboard-container">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>checkpoints</span>
        <el-button style="right: 40px; position: absolute;" type="primary" size="small" @click="addCheckpoint()">新增checkpoint</el-button>
      </div>
      <el-table :data="list" border fit highlight-current-row style="width: 100%">
        <el-table-column prop="id" label="编号"></el-table-column>
        <el-table-column prop="name" label="规则集名"></el-table-column>
        <el-table-column label="命中策略">
          <template slot-scope="scope">
            <span>{{actionMap[scope.row.action]}}</span>
          </template>
        </el-table-column>
        <el-table-column label="更新时间">
           <template slot-scope="scope">
            <span>{{scope.row.createdTime / 1000 | moment("YYYY-MM-DD ss:mm") }}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="primary" @click="modify(scope.row)" size="mini" icon="el-icon-edit">修改</el-button>
            <el-button type="danger" @click="removeDrl(scope.row)" size="mini" icon="el-icon-delete">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>workflow + decision</span>
         <el-button style="right: 100px; position: absolute;" type="primary" size="small" @click="addFlow()">新增流程</el-button>
        <el-button style="right: 40px; position: absolute;" type="primary" size="small" @click="saveFlow()">保存</el-button>
      </div>
      <div class="flow-id">
        id：{{this.workflowId}}
      </div>
      <div class="block">
        <el-tree
          :data="data"
          default-expand-all
          :expand-on-click-node="false"
          :render-content="renderContent"
          :props="defaultProps">
        </el-tree>
      </div>      
    </el-card>
    <el-dialog :visible.sync="dialogFormVisible">
      <el-form :model="form" ref="ruleForm" label-width="180px" class="demo-ruleForm">
        <el-form-item label="如果命中该drl，则运行" prop="variable">
          <el-select v-model="form.hit" placeholder="请选择规则集" clearable>
            <el-option v-for="t in list" :key="t.id" :label="t.name" :value="t.id"></el-option>
          </el-select>
          或
          <el-select v-model="form.hitAction" placeholder="请选择策略" clearable>
            <el-option v-for="t in Object.keys(actionMap)" :key="t" :label="actionMap[t]" :value="t"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="否则，运行" prop="variable">
          <el-select v-model="form.nothit" placeholder="请选择规则集" clearable>
            <el-option v-for="t in list" :key="t.id" :label="t.name" :value="t.id"></el-option>
          </el-select>
          或
          <el-select v-model="form.nothitAction" placeholder="请选择策略" clearable>
            <el-option v-for="t in Object.keys(actionMap)" :key="t" :label="actionMap[t]" :value="t"></el-option>
          </el-select> 
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="addChild()">保存</el-button>
      </div>
    </el-dialog>
    <el-dialog :visible.sync="flowVisible">
      <el-form :model="flow" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="新流程" prop="variable">
          <el-select v-model="flow.id" placeholder="请选择规则集" clearable>
            <el-option v-for="t in list" :key="t.id" :label="t.name" :value="t.id">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="flowVisible = false">取消</el-button>
        <el-button type="primary" @click="addFlowSave()">保存</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { insertFlow, activateRule, getList, getActions, deleteDrl } from '@/api/rule'
import { getFlow } from '@/api/rule'
let nid = 100
export default {
  name: 'dashboard',
  data() {
    return {
      list: [],
      data: [],
      workflowId: '',
      dialogFormVisible: false,
      flowVisible: false,
      form: {},
      flow: {
        id: ''
      },
      currentNode: {},
      nodeMap: {
        'rule': {},
        'action': {}
      },
      defaultProps: {
      },
      actionMap: {}
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    append(data) {
      if (data.label === '' || data.type === 'action') {
        this.$message('不能继续添加流程')
        return
      }
      this.currentNode = data
      this.form = { hit: '', nothit: '', hitAction: '', nothitAction: '' }
      this.dialogFormVisible = true
    },
    modifySub(data) {
      this.currentNode = data
      this.form = { hit: '', nothit: '', hitAction: '', nothitAction: '' }
      this.dialogFormVisible = true
    },
    remove(node, data) {
      const parent = node.parent
      if (!(parent.data instanceof Array)) {
        this.$set(parent.data, 'children', [])
      } else {
        const children = parent.data
        const index = children.indexOf(data)
        // const index = children.findIndex(d => d.id === data.id)
        children.splice(index, 1)
      }
    },
    fetchData() {
      getList().then(response => {
        this.list = response.data
        this.list.forEach(function(element) {
          this.nodeMap['rule'][element.id + ''] = element.name
        }, this)
        getActions().then(response => {
          this.actionMap = response.data
          this.nodeMap['action'] = this.actionMap
        })
        getFlow().then(flowResp => {
          if (Object.keys(flowResp.data).indexOf('workflow') !== -1) {
            this.data = JSON.parse(flowResp.data.workflow)
            this.workflowId = flowResp.data.id
          }
        })
        this.defaultProps['nodeMap'] = this.nodeMap
      })
    },
    modify(row) {
      this.$router.push('/engine/drl?id=' + row.id)
    },
    removeDrl(row) {
      deleteDrl({ id: row.id }).then(response => {
        this.$message('删除成功')
        this.fetchData()
      })
    },
    renderContent(h, { node, data, store }) {
      return (
        <span class='custom-tree-node'>
          <span>{store.props.nodeMap[data.type][data.label]}</span>
          <span>
            <el-button size='mini' type='text' on-click={ () => this.append(data) }>增加子流程</el-button>
            <el-button size='mini' type='text' on-click={ () => this.remove(node, data) }>删除该流程</el-button>
          </span>
        </span>)
    },
    filterList(list) {
      return list.filter(data => {
        return data.enabled === true
      })
    },
    addChild() {
      var h = ''
      var t = ''
      var nh = ''
      var nt = ''
      if (this.form.hit !== '') {
        h = this.form.hit
        t = 'rule'
      } else if (this.form.hitAction !== '') {
        h = this.form.hitAction
        t = 'action'
      }
      if (this.form.nothit !== '') {
        nh = this.form.nothit
        nt = 'rule'
      } else if (this.form.nothitAction !== '') {
        nh = this.form.nothitAction
        nt = 'action'
      }
      const hitChild = { id: nid++, label: h, type: t, children: [] }
      const nothitChild = { id: nid++, label: nh, type: nt, children: [] }
      this.$set(this.currentNode, 'children', [])
      this.currentNode.children.push(hitChild)
      this.currentNode.children.push(nothitChild)
      this.dialogFormVisible = false
    },
    saveFlow() {
      insertFlow({ 'workflow': JSON.stringify(this.data) }).then(response => {
        this.fetchData()
        this.$message('保存成功')
      })
    },
    addFlow() {
      this.flowVisible = true
    },
    addFlowSave() {
      const child = { id: nid++, label: this.flow.id, children: [], type: 'rule' }
      this.data.push(child)
      this.flowVisible = false
    },
    enableDrl(data) {
      if (data.enabled) {
        this.$message('规则已启用')
      }
      activateRule({ id: data.id, enabled: 1 }).then(response => {
        this.$message('启用成功')
        this.fetchData()
      })
    },
    disableDrl(data) {
      if (!data.enabled) {
        this.$message('规则已关闭')
      }
      activateRule({ id: data.id, enabled: 0 }).then(response => {
        this.$message('关闭成功')
        this.fetchData()
      })
    },
    addCheckpoint() {
      this.$router.push('/engine/drl')
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
.enable_rule_button {
  right: 30px;
  position: absolute;
}
</style>

<style>
.custom-tree-node {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 14px;
  padding-right: 8px;
}
.flow-id {
  margin-bottom: 10px;
}
</style>