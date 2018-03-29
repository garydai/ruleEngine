<template>
  <div class="dashboard-container">
    <div class="fixed-div">
      <el-button type="primary" size="small" @click="handleAddRule()">增加规则</el-button>
      <el-button type="primary" size="small" @click="handleUpdateRule()">提交修改</el-button>
    </div>
    <el-card class="box-card">
      <span>checkoutpoint</span>
      <el-input style="width: 150px;" class="filter-item" v-model="rule.name"></el-input>
    </el-card>
    <div class="rules" style="margin-top: 20px">
      <el-row v-for="(item, index) in list" :key="index">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>规则</span>
            <el-input style="width: 200px;" class="filter-item" placeholder="必选" v-model="list[index].name">
            </el-input>
          </div>
          <div slot="header" class="clearfix" style="margin-top:10px">
            </el-input>
            <span>类别</span>
            <el-input style="width: 200px;" class="filter-item" placeholder="可选" v-model="list[index].source">
            </el-input>
            <span>描述</span>
            <el-input style="width: 200px;" class="filter-item" placeholder="可选" v-model="list[index].detail.desc.value">
            </el-input>
            <span>优先级</span>
            <el-input style="width: 100px;" class="filter-item" placeholder="可选" v-model="list[index].level">
            </el-input>
            <el-button type="danger" size="mini" @click="handleDeleteRule(index)">删除规则
            </el-button>      
          </div>
          <el-button size="mini" type="primary" @click="handleAddRelation(index)">增加表达式    
          </el-button>
          <el-button type="danger" size="mini" @click="handleDeleteRelation(index)">删除表达式
          </el-button>
          <div style="margin-bottom:50px;">
              <el-table :data="item.rule" style="width: 100%">
                <el-table-column width="180">
                  <template slot-scope="scope">
                    <el-select filterable v-if="scope.$index % 2 == 0" style="width: 150px" class="filter-item" v-model="scope.row.l" clearable>
                      <el-option v-for="t in Object.keys(variables)" :key="t" :label="mapper[t]" :value="mapper[t]">
                      </el-option>
                    </el-select>
                  </template>
                </el-table-column>
                <el-table-column width="180">
                  <template slot-scope="scope">
                    <el-select v-if="scope.$index % 2 == 0 && scope.row.l == ''" style="width: 150px" class="filter-item" v-model="scope.row.o" clearable>
                      <el-option :value="''">
                      </el-option>
                    </el-select>
                    <el-select v-else-if="scope.$index % 2 == 0 && scope.row.l != ''" style="width: 150px" class="filter-item" v-model="scope.row.o" clearable>
                      <el-option v-for="t in op[scope.$index % 2][variables[mapper[scope.row.l]].type]" :key="t" :label="t" :value="t">
                      </el-option>
                    </el-select>
                    <el-select v-else-if="scope.$index % 2 == 1" style="width: 150px" class="filter-item" v-model="scope.row.o" clearable>
                      <el-option v-for="t in op[scope.$index % 2]" :key="t" :label="t" :value="t">
                      </el-option>
                    </el-select>
                  </template>
                </el-table-column>
                <el-table-column>
                  <template slot-scope="scope">
                    <span v-if="scope.$index % 2 == 0" class="link-type" @click="handleUpdateRight(scope.row)">
                      <div v-if="scope.row.r == ''">修改</div>
                      <div v-else>{{scope.row.r}}</div>
                    </span>
                    <!--<el-input v-if="scope.$index % 2 == 0" style="width: 150px;" class="filter-item" :placeholder="list[index].rule[scope.$index].r" v-model="list[index].rule[scope.$index].r">-->
                    <!--</el-input>-->
                  </template>
                </el-table-column>
            </el-table>       
          </div>        
        </el-card>
      </el-row>
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>命中规则</span>
        </div>
        <div style="margin-bottom:50px;">
          <template>
            <el-radio v-model="hitRadio" label="or">满足任意规则</el-radio>
            <el-radio v-model="hitRadio" label="and">满足所有规则</el-radio>  
            <el-radio v-model="hitRadio" label="">使用表达式</el-radio>            
          </template>
        </div>
      </el-card>
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>表达式</span>
          <el-button style="right: 40px; position: absolute;" type="primary" size="small" @click="addRootExpression()">新增根表达式</el-button>
        </div>
        <div class="block">
          <el-tree
            :data="flow"
            default-expand-all
            :expand-on-click-node="false"
            :render-content="renderContent">
          </el-tree>
        </div>
      </el-card>
    </div>
    <el-dialog :visible.sync="flowFormVisible">
      <el-select v-model="flowSelect" placeholder="请选择操作符" filterable clearable>
        <el-option v-for="item in operations" :key="item.op" :label="item.op" :value="item.op"></el-option>
      </el-select>或
      <el-select v-model="flowRuleSelect" placeholder="请选择规则" filterable clearable>
        <el-option v-for="item in list" :key="item.id" :label="item.name" :value="item.name"></el-option>
      </el-select>
      <div slot="footer" class="dialog-footer">
        <el-button @click="flowFormVisible = false">取消</el-button>
        <el-button type="primary" @click="saveExpression()">保存</el-button>
      </div>
    </el-dialog>
    <el-dialog :visible.sync="dialogFormVisible">
      <!--<el-input v-model="form.value" placeholder="请填写数值" :disabled="curRuleLeftType === 'Boolean'"></el-input>
      <el-select v-model="form.determine" placeholder="请选择" :disabled="curRuleLeftType !== 'Boolean'" clearable>
        <el-option v-for="t in boolList" :key="t" :label="t" :value="t">
        </el-option>
      </el-select>
      <el-input v-model="form.value" placeholder="请填写数值" :disabled="curRuleLeftType === 'Boolean'"></el-input>-->
      <el-form label-width="100px" class="demo-ruleForm">
        <el-form-item label="数值">
          <el-input v-model="form.value" placeholder="请填写数值" :disabled="curRuleLeftType === 'Boolean'"></el-input>
        </el-form-item>
        <el-form-item label="是否">
          <el-select v-model="form.determine" placeholder="请选择" :disabled="curRuleLeftType !== 'Boolean'" clearable>
            <el-option v-for="t in boolList" :key="t" :label="t" :value="t">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="变量">
          <el-select v-model="form.variable" placeholder="请选择变量" clearable filterable>
            <el-option v-for="t in Object.keys(variables)" :key="t" :label="mapper[t]" :value="mapper[t]">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="saveRightValue()">保存</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getDrl, addRule, getVariables, updateRule } from '@/api/rule'
import { clone } from '@/utils/util'
const constant = require('@/utils/constant')
let nid = 100

export default {
  name: 'dashboard',
  data() {
    return {
      form: {
        value: '',
        variable: '',
        determine: ''
      },
      formRule: {
      },
      list: [],
      variables: {},
      // todo refine
      listDisplay: [],
      mapper: {}, // chinese->english
      op: [],
      dialogFormVisible: false,
      curRule: {},
      curRuleLeftType: '',
      boolList: ['是', '否'],
      hitRadio: 'or',
      listLoading: true,
      rule: {},
      operations: [
        { op: 'and' },
        { op: 'or' }
      ],
      flowSelect: '',
      flowRuleSelect: '',
      flow: [],
      flowFormVisible: false,
      currentNode: {},
      actionSelect: '',
      actionMap: {},
      actionConstant: {}
    }
  },
  created() {
    this.mapper = constant.m
    this.op = constant.opMap
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.listLoading = true
      getVariables().then(response => {
        this.variables = response.data
        var nul = {
          'type': 'null',
          'displayName': '空值'
        }
        this.variables['null'] = nul

        for (var key in this.variables) {
          this.mapper[this.variables[key].displayName] = key
          this.mapper[key] = this.variables[key].displayName
        }
        if (Object.keys(this.$route.query).indexOf('id') === -1) {
          return
        }
        getDrl(this.$route.query.id).then(response => {
          this.rule = response.data
          var input = JSON.parse(response.data.input)
          this.hitRadio = input.expression.coarse
          this.flow = input.expression.fine
          this.list = input.rules
          this.list.forEach(function(element) {
            element.name = element.name.replace(/^"(.*)"$/, '$1')
            element.rule.forEach(function(ele) {
              if (ele.l in this.mapper) {
                ele.l = this.mapper[ele.l]
              }
              if (ele.o in this.mapper) {
                ele.o = this.mapper[ele.o]
              }
              if ((ele.r_t === 'v' || ele.r_t === 'Boolean') && ele.r in this.mapper) {
                ele.r = this.mapper[ele.r]
              }
              ele.r = ele.r.replace(/^"(.*)"$/, '$1')
            }, this)
          }, this)
          this.listLoading = false
        })
      })
    },
    handleDeleteRelation(listIdx) {
      this.list[listIdx].rule.splice(-1, 1)
    },
    handleDeleteRule(listIdx) {
      this.list.splice(listIdx, 1)
    },
    handleUpdateRule() {
      // todo 增量修改
      var listCopy = clone(this.list)
      var result = {
        ver: constant.ruleVersion,
        rules: listCopy,
        expression: {}
      }
      var valid = true
      var variables = []
      var variablesMap = {}
      listCopy.forEach(function(element) {
        if (element.name === '') {
          valid = false
        }
        var rule = element.rule
        var v = []
        rule.forEach(function(element, index) {
          if (element.l in this.mapper) {
            element.l = this.mapper[element.l]
          }
          if (element.o in this.mapper) {
            element.o = this.mapper[element.o]
          }
          if (element.r_t !== 'v' && element.r !== '') {
            element.r_t = this.variables[element.l].type.replace('List.', '') // 非variable, 类型同left
          }
          if (element.r_t === 'v' || element.r_t === 'Boolean') {
            element.r = this.mapper[element.r]
          }
          if (element.r_t.indexOf('String') !== -1) {
            element.r = '\"' + element.r + '\"'
          } else if (element.r !== '' && element.r[0] === '"' && element.r[element.r.length - 1] === '"') {
            element.r = element.r.replace(/^"(.*)"$/, '$1')
          }

          if (index % 2 === 0) {
            valid = (element.l !== '' && element.o !== '' && element.r !== '')
          }
          if (index % 2 === 1) {
            valid = (element.o !== '')
          }

          if (element.l !== '') {
            this.push(variables, variablesMap, element.l)
            this.pushRuleVariable(v, element.l)
          }
          if (element.r_t === 'v') {
            this.push(variables, variablesMap, element.r)
            this.pushRuleVariable(v, element.r)
          }
        }, this)
        element.detail.variable.members = v
        if (rule.length % 2 === 0) {
          valid = false
        }
      }, this)
      if (!valid) {
        this.$message('请填写完整')
        return
      }
      result.variables = variables
      result.expression.coarse = this.hitRadio
      result.expression.fine = this.flow
      if (this.rule && this.rule.id) {
        updateRule({ input: JSON.stringify(result), id: this.rule.id, name: this.rule.name, sceneId: this.$route.query.sceneId }).then(response => {
          this.$message('保存成功')
          this.fetchData()
        })
      } else {
        addRule({ input: JSON.stringify(result), name: this.rule.name, sceneId: this.$route.query.sceneId }).then(response => {
          this.$router.push('/engine/rule')
        })
      }
    },
    pushRuleVariable(v, value) {
      console.log(v)
      console.log(value)
      console.log(this.variables[value])
      if (value !== 'null' && (this.variables[value].visibility === undefined || this.variables[value].visibility === 'false')) {
        v.push(value)
      }
    },
    push(variables, variablesMap, value) {
      if (value === 'null') {
        return
      }
      if (!(value in variablesMap)) {
        variables.push(value)
        variablesMap[value] = 1
      }
    },
    handleAddRelation(listIdx) {
      var item = {
        l: '',
        o: '',
        r: '',
        r_t: ''
      }
      this.list[listIdx].rule.push(item)
    },
    handleAddRule(listIdx) {
      var rule = {
        'name': '',
        'rule': [],
        'level': '',
        'source': '',
        'detail': {
          'desc': {
            'enabled': false,
            'value': ''
          },
          'variable': {
            'enabled': false,
            'members': []
          },
          'udf': {
            'enabled': false,
            'variables': []
          }
        }
      }
      this.list.push(rule)
    },
    handleUpdateRight(rule) {
      this.curRule = rule
      this.curRuleLeftType = this.variables[this.mapper[rule.l]].type
      this.form.variable = ''
      this.form.value = ''
      this.form.determine = ''
      this.dialogFormVisible = true
    },
    saveRightValue() {
      if (this.form.determine !== '' && this.curRuleLeftType === 'Boolean') {
        // bool型
        this.curRule.r = this.form.determine
        this.curRule.r_t = 'Boolean'
      } else if (this.form.value !== '') { // 优先数值
        this.curRule.r = this.form.value
        this.curRule.r_t = '' // 类型由最后提交的时候修改
      } else if (this.form.variable !== '') {
        this.curRule.r = this.form.variable // 优先数值
        this.curRule.r_t = 'v' // 变量
      }
      this.dialogFormVisible = false
    },
    renderContent(h, { node, data, store }) {
      return (
        <span class='custom-tree-node'>
          <span>{node.label}</span>
          <span>
            <el-button size='mini' type='text' on-click={ () => this.appendExpression(data) }>增加子表达式</el-button>
            <el-button size='mini' type='text' on-click={ () => this.removeExpression(node, data) }>删除该表达式</el-button>
          </span>
        </span>)
    },
    addRootExpression() {
      this.flowSelect = ''
      this.flowRuleSelect = ''
      this.flowFormVisible = true
      this.currentNode = this.flow
    },
    saveExpression() {
      var select = ''
      if (this.flowSelect !== '') {
        select = this.flowSelect
      } else if (this.flowRuleSelect !== '') {
        select = this.flowRuleSelect
      }
      if (select === '') {
        this.flowFormVisible = false
        return
      }
      if (this.currentNode === this.flow) {
        // root
        const oldChild = clone(this.flow)
        const newChild = { id: nid++, label: select, children: oldChild }
        this.flow = []
        this.flow.push(newChild)
      } else {
        const newChild = { id: nid++, label: select, children: [] }
        this.currentNode.children.push(newChild)
      }
      this.flowFormVisible = false
    },
    appendExpression(data) {
      if (data.label !== 'or' && data.label !== 'and') {
        this.$message('不能添加')
        return
      }
      this.currentNode = data
      this.flowSelect = ''
      this.flowRuleSelect = ''
      this.flowFormVisible = true
    },
    removeExpression(node, data) {
      const parent = node.parent
      if (!(parent.data instanceof Array)) {
        const index = parent.data.children.indexOf(data)
        parent.data.children.splice(index, 1)
      } else {
        // root
        const children = parent.data
        const index = children.indexOf(data)
        children.splice(index, 1)
      }
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
.fixed-div {
  position: fixed;
  top: 80px;
  right: 30px;
  bottom: 0px;
  margin: 0;
  z-index: 2000;
  height: 40px;
}
</style>