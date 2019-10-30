<template>
  <div class="dashboard-container">
    <el-card class="box-card" v-for="(item, index) in list" :key="index">
      <div slot="header" class="clearfix">
        <span>当前workflow版本（{{index == 0 ? '测试环境': '线上环境'}}）</span>
      </div>
      <div style="margin-bottom:50px;">
        <el-table :data="item" style="width: 100%">
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
              <span class="link-type" @click="getHistory(scope.row, index)">{{scope.row.flowId}}</span>
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
    getHistory(row, index) {
      this.$router.push('/engine/version/history?sceneId=' + row.sceneId + '&env=' + index)
    },
    fetchData() {
      this.list = []
      getVersion(0).then(response => {
        this.list.push(response.data)
        getVersion(1).then(response => {
          this.list.push(response.data)
        })
      })
    }
  }
}
</script>