<template>
  <div id="app">
    <el-table
      :data="tableData"
      v-loading="listLoading"
      element-loading-text="加载中..."
      stripe
      border
      style="width: 100%">
      <el-table-column
        prop="username"
        label="用户名">
      </el-table-column>
      <el-table-column
        prop="email"
        label="邮箱">
      </el-table-column>
      <el-table-column
        label="删除状态">
        <template slot-scope="scope">
          <el-tag type="danger" v-if="scope.row.status=='0'">禁用</el-tag>
          <el-tag type="success" v-if="scope.row.status=='1'">启用</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        label="操作">
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="handleEdit(scope.$index, scope.row)">分配角色</el-button>
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div class="block" v-if="pages<7">
      <el-pagination
        layout="total,sizes,prev, pager, next, jumper"
        :size="pageNum"
        :total="total"
        @prev-click="prev"
        @next-click="next"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange">
      </el-pagination>
    </div>
    <div class="block" v-if="pages>=7">
      <el-pagination
        layout="total,sizes,prev, pager, next, jumper"
        :size="pageNum"
        :total="total"
        @prev-click="prev"
        @next-click="next"
        @current-change="handleCurrentChange">
      </el-pagination>
    </div>
  </div>
</template>

<script>
  import {getUserList} from '@/api/system'
    export default {
        name: "user",
      data(){
          return{
            listLoading:true,
            tableData: [],
            pageSize:1,
            pageNum:10,
            pages:null,
            total:null,
          }
      },
      created() {
        this.fetchData();
      },
      methods:{
        fetchData() {
          this.listLoading = true
          getUserList({pageSize:this.pageSize,pageNum:this.pageNum}).then(response => {
            this.tableData = response.data.list
            this.pages=response.data.pages
            this.total=response.data.total
            this.listLoading = false
          }).catch(()=>{
            this.listLoading = false
          })
        },
        prev(val){
          this.pageSize=val;
          this.fetchData();
        },
        next(val){
          this.pageSize=val;
          this.fetchData();
        },
        handleCurrentChange(val){
          this.pageSize=val;
          this.fetchData();
        },
        handleSizeChange(val){
          this.pageNum=val;
          this.fetchData();
        },
        handleEdit(index,row){
          this.$router.push({path:'/system/role',query:{id:row.id}})
        }
      },
    }
</script>

<style scoped>

</style>
