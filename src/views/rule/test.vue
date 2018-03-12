<template>
  <div class="app-container">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>输入</span>
      </div>
      <el-button size="mini" type="primary" @click="handleAddCondition()">添加条件    
      </el-button>
      <div style="margin-bottom:50px;">
        <el-table :data="inputs" style="width: 100%">
          <el-table-column width="200">
            <template slot-scope="scope">
              <el-select filterable clearable class="filter-item" v-model="scope.row.key">
                <el-option v-for="t in Object.keys(variables)" :key="t" :label="mapper[t]" :value="mapper[t]">
                </el-option>
              </el-select>
            </template>
          </el-table-column>
          <el-table-column width="180">
            <template slot-scope="scope">
              <el-input style="width: 150px;" class="filter-item" placeholder="请输入" v-model="scope.row.value">
              </el-input>
            </template>
          </el-table-column>
          <el-table-column
            width="180">
            <template slot-scope="scope">
              <el-button
                @click.native.prevent="deleteRow(scope.$index)"
                type="text"
                size="small">
                移除
              </el-button>
            </template>
          </el-table-column>
        </el-table>
        <div style="margin-top: 30px">
          <el-button size="mini" type="primary" @click="onSubmit">发送</el-button>
        </div>
      </div>
    </el-card>
    <el-card>
      <div slot="header" class="clearfix">
        <span>测试用例</span>
      </div>
      <div>
        <el-button size="mini" type="primary" @click="onTest">运行</el-button>
      </div>
    </el-card>
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>结果</span>
      </div>
      <div style="margin-bottom:50px;">
        <template>
          <pre>{{result}}</pre>
        </template>
      </div>
    </el-card>
  </div>
</template>

<script>
import { getVariables, testRule } from '@/api/rule'

export default {
  data() {
    return {
      variables: {},
      inputs: [],
      result: '',
      mapper: {}
    }
  },
  created() {
    this.fetchData()
    var inputString = localStorage.getItem('testInput')
    if (inputString) {
      this.inputs = JSON.parse(inputString)
    }
  },
  methods: {
    deleteRow(index) {
      this.inputs.splice(index, 1)
    },
    fetchData() {
      getVariables().then(response => {
        for (var key in response.data) {
          this.variables[key] = response.data[key]
        }
        for (var key2 in this.variables) {
          this.mapper[this.variables[key2].displayName] = key2
          this.mapper[key2] = this.variables[key2].displayName
        }
      })
    },
    onSubmit() {
      localStorage.setItem('testInput', JSON.stringify(this.inputs))
      var p = {}
      this.inputs.forEach(function(element) {
        if (element.key !== '' && element.value !== '') {
          p[this.mapper[element.key]] = element.value
        }
      }, this)
      this.result = ''
      if (Object.keys(p).length === 0) {
        this.$message('请添加条件')
      }
      var test = ''
      Object.keys(p).forEach(function(item) {
        switch (this.variables[item]['type']) {
          case 'Integer':
            p[item] = parseInt(p[item])
            break
          case 'Double':
            p[item] = parseFloat(p[item])
            break
          case 'Boolean':
            p[item] = parseInt(p[item])
            break
          case 'String':
            p[item] = '"' + p[item] + '"'
            break
        }
        test = test + ',' + item + ':' + p[item]
        console.log(test)
      }, this)
      test = test.substr(1)
      testRule({ 'req': '{' + test + '}' }).then(response => {
        this.result = JSON.stringify(response.data, null, 2)
      })
    },
    handleAddCondition() {
      var condition = {
        key: '',
        value: ''
      }
      this.inputs.push(condition)
    },
    onTest() {
      this.result = ''
      testRule().then(response => {
        this.result = JSON.stringify(response.data, null, 2)
      })
    }
  }
}
</script>

<style scoped>
.line{
  text-align: center;
}
</style>

