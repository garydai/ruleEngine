<template>
  <div class="dashboard-container">
    <el-card class="box-card">
      <div style="margin-bottom:50px;">
        <el-table :data="list" style="width: 100%">
          <el-table-column width="180" label="序号">
            <template slot-scope="scope">
              <span>{{scope.$index}}</span>
            </template>
          </el-table-column>
          <el-table-column width="180" label="变量名">
            <template slot-scope="scope">
              <span>{{list[scope.$index].name}}</span>
            </template>
          </el-table-column>
          <el-table-column width="180" label="变量描述">
            <template slot-scope="scope">
              <span>{{list[scope.$index].description}}</span>
            </template>
          </el-table-column>
          <el-table-column width="180" label="类型">
            <template slot-scope="scope">
              <span>{{list[scope.$index].type}}</span>
            </template>
          </el-table-column>
          <!-- <el-table-column align="right">
            <template slot-scope="scope">
              <el-button
                size="mini"
                @click="handleEdit(scope.$index, scope.row)">Edit</el-button>
              <el-button
                size="mini"
                type="danger"
                @click="handleDelete(scope.$index, scope.row)">Delete</el-button>
            </template>
          </el-table-column> -->
        </el-table>
      </div>
    </el-card>
  </div>
</template>

<script>
import { listVariable } from '@/api/variable'
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
      listVariable().then(response => {
        this.list = response.data.list
      })
    }
  }
}
</script>