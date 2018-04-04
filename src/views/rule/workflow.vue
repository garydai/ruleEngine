<template>
  <div class="dashboard-container">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>checkpoints</span>
        <el-button style="right: 40px; position: absolute;" type="primary" size="small" @click="addCheckpoint()">新增checkpoint</el-button>
      </div>
      <el-table v-loading="loading" :data="list" border fit highlight-current-row style="width: 100%">
        <el-table-column prop="id" label="编号"></el-table-column>
        <el-table-column prop="name" label="规则集名"></el-table-column>
        <el-table-column prop="version" label="当前版本"></el-table-column>
        <el-table-column label="更新时间">
           <template slot-scope="scope">
            <span>{{scope.row.createTime / 1000 | moment("YYYY-MM-DD hh:mm") }}</span>
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
        <span>workflow（更新规则后，请更新workflow，否则更新的规则不生效）</span>
         <el-button style="right: 130px; position: absolute;" type="primary" size="small" @click="addFlow()">新增流程</el-button>
        <el-button style="right: 40px; position: absolute;" type="primary" size="small" @click="saveFlow()">提交更新</el-button>
      </div>
      <div class="flow-id">
        编号：{{this.workflowId}}
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
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>测试</span>
      </div>
      <div class="test">
        <el-button style="" type="primary" size="small" @click="test()">测试</el-button>
      </div>
    </el-card>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>部署测试环境</span>
      </div>
      <div class="test">
        <el-button style="" type="primary" size="small" @click="staging()">部署</el-button>
      </div>
    </el-card>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>部署到线上</span>
      </div>
      <div class="test">
        <el-button style="" type="primary" size="small" @click="prod()">部署</el-button>
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
import { insertFlow, activateRule, getList, getActions, deleteDrl, getFlow, deployTest, deployProd } from '@/api/rule'
import { walkTree } from '@/utils/util'
let nid = 100
export default {
  name: 'dashboard',
  data() {
    return {
      list: [],
      allRuleVersion: {},
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
      actionMap: {},
      loading: false
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    staging() {
      deployTest(this.workflowId).then(response => {
        this.$message('部署成功')
      })
    },
    prod() {
      deployProd(this.workflowId).then(response => {
        this.$message('部署成功')
      })
    },
    test() {
      this.$router.push('/engine/test?sceneId=' + this.$route.query.sceneId)
    },
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
      this.loading = true
      getList(this.$route.query.sceneId).then(response => {
        this.list = response.data
        if (this.list == null) {
          this.list = []
        }
        this.allRuleVersion = {}
        this.list.forEach(function(element) {
          this.nodeMap['rule'][element.id + ''] = element.name
          this.allRuleVersion[element.id + ''] = element.version
        }, this)
        getActions().then(response => {
          this.actionMap = response.data
          this.nodeMap['action'] = this.actionMap
        })
        getFlow({ sceneId: this.$route.query.sceneId }).then(flowResp => {
          if (Object.keys(flowResp.data).indexOf('workflow') !== -1) {
            this.data = JSON.parse(flowResp.data.workflow)
            this.workflowId = flowResp.data.id
          }
        })
        this.defaultProps['nodeMap'] = this.nodeMap
      }).finally(() => {
        this.loading = false
      })
    },
    modify(row) {
      this.$router.push('/engine/drl?id=' + row.id)
    },
    removeDrl(row) {
      this.$confirm('是否确定删除该规则?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.confirmRemoveDrl(row)
      })
    },
    confirmRemoveDrl(row) {
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
      this.data.forEach(function(ele) {
        var ruleArray = []
        walkTree(ele, ruleArray)
        var ruleVersion = {}
        ruleArray.forEach(function(ele) {
          ruleVersion[ele + ''] = this.allRuleVersion[ele + '']
        }, this)
        ele['ruleVersion'] = ruleVersion
      }, this)
      insertFlow({ workflow: JSON.stringify(this.data), sceneId: this.$route.query.sceneId }).then(response => {
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
      this.$router.push('/engine/drl?sceneId=' + this.$route.query.sceneId)
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