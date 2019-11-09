<template>
  <div class="dashboard-container">
    <el-card class="box-card">
      <div style="margin-bottom:50px;">
        <el-table :data="list" style="width: 100%">
          <el-table-column width="180" label="变量名">
            <template slot-scope="scope">
              <span>{{list[scope.$index]}}</span>
            </template>
          </el-table-column>
          <el-table-column width="180" label="变量别名">
            <template slot-scope="scope">
              <span>{{mapper[list[scope.$index]]}}</span>
            </template>
          </el-table-column>
          <el-table-column width="180" label="类型">
            <template slot-scope="scope">
              <span>{{variables[list[scope.$index]].type}}</span>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </el-card>
  </div>
</template>

<script>
import { getVariables } from '@/api/rule'
const constant = require('@/utils/constant')

export default {
  data() {
    return {
      list: [],
      variables: {},
      mapper: {}
    }
  },
  created() {
    this.mapper = constant.m
    this.fetchData()
  },
  methods: {
    fetchData() {
      getVariables().then(response => {
        this.variables = response.data
        for (var key in this.variables) {
          this.mapper[this.variables[key].displayName] = key
          this.mapper[key] = this.variables[key].displayName
        }
        this.list = Object.keys(response.data)
      })
    }
  }
}
</script>