<template>
  <div class="dashboard-container">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>当前workflow版本</span>
      </div>
      <div style="margin-bottom:50px;">
        <el-table :data="list" style="width: 100%">
          <el-table-column width="180" label="id">
            <template slot-scope="scope">
              <span>{{scope.row.id}}</span>
            </template>
          </el-table-column>
          <el-table-column width="180" label="scene">
            <template slot-scope="scope">
              <span>{{scope.row.sceneId}}</span>
            </template>
          </el-table-column>
          <el-table-column width="180" label="workflow">
            <template slot-scope="scope">
              <span>{{scope.row.flowId}}</span>
            </template>
          </el-table-column>
          <el-table-column label="更新时间">
           <template slot-scope="scope">
            <span>{{scope.row.updateTime / 1000 | moment("YYYY-MM-DD hh:mm") }}</span>
          </template>
        </el-table-column>
        </el-table>
      </div>
    </el-card>
  </div>
</template>

<script>
import { getVersion } from '@/api/rule'

export default {
  data() {
    return {
      list: []
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      getVersion().then(response => {
        this.list = response.data
      })
    }
  }
}
</script>