<template>
  <div class="dashboard-container">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>场景</span>
        <el-button style="right: 40px; position: absolute;" type="primary" size="small" @click="addScene()">新增场景</el-button>
      </div>
      <el-table :data="list" border fit highlight-current-row style="width: 100%">
        <el-table-column prop="id" label="编号"></el-table-column>
        <el-table-column prop="name" label="场景"></el-table-column>
        <el-table-column label="更新时间">
           <template slot-scope="scope">
            <span>{{scope.row.createTime / 1000 | moment("YYYY-MM-DD hh:mm") }}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button type="primary" @click="modify(scope.row)" size="mini" icon="el-icon-edit">修改</el-button>
            <el-button type="primary" @click="workflow(scope.row)" size="mini" icon="el-icon-edit">规则</el-button>
            <el-button type="danger" @click="removeScene(scope.row)" size="mini" icon="el-icon-delete">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <el-dialog :visible.sync="visible">
      <el-form> 
        <el-form-item label-position="left" label-width="70px" style='width: 400px; margin-left:50px;'>
          <span>场景</span>
          <el-input style="width: 200px;" class="filter-item" placeholder="请填写" v-model="sceneName"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="visible = false">取消</el-button>
        <el-button type="primary" @click="saveSceneName()">保存</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getSceneList, addScene, updateScene, deleteScene } from '@/api/scene'
export default {
  name: 'scene',
  data() {
    return {
      list: [],
      sceneName: '',
      visible: false,
      updateFlat: -1,
      curId: -1
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    workflow(data) {
      this.$router.push('/engine/rule?sceneId=' + data.id)
    },
    modify(data) {
      this.visible = true
      this.curId = data.id
    },
    addScene() {
      this.visible = true
      this.sceneName = ''
      this.curId = -1
    },
    saveSceneName() {
      if (this.curId === -1) {
        addScene({ sceneName: this.sceneName }).then(response => {
          this.visible = false
          this.fetchData()
        })
      } else {
        updateScene(this.curId, { name: this.sceneName }).then(response => {
          this.visible = false
          this.fetchData()
        })
      }
    },
    fetchData() {
      getSceneList().then(response => {
        this.list = response.data
      })
    },
    removeScene(data) {
      deleteScene(data.id).then(response => {
        this.$message('删除成功')
        this.fetchData()
      })
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