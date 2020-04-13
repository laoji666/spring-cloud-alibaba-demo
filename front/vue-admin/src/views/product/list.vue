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
        prop="name"
        label="商品名">
      </el-table-column>
      <el-table-column
        prop="pic"
        label="图片">
      </el-table-column>
      <el-table-column
        prop="price"
        label="售价">
      </el-table-column>
      <el-table-column
        prop="sale"
        label="销量">
      </el-table-column>
      <el-table-column
        label="删除状态">
        <template slot-scope="scope">
          <el-tag type="success" v-if="scope.row.deleteStatus=='0'">未删除</el-tag>
          <el-tag type="danger" v-if="scope.row.deleteStatus=='1'">已删除</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        label="上架状态">
        <template slot-scope="scope">
          <el-tag type="success" v-if="scope.row.publishStatus=='1'">上架</el-tag>
          <el-tag type="danger" v-if="scope.row.publishStatus=='0'">下架</el-tag>
        </template>
      </el-table-column>
      <el-table-column
        label="操作">
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
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
  import {getList} from '@/api/product'
    export default {
        name: "list",
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
          getList({pageSize:this.pageSize,pageNum:this.pageNum}).then(response => {
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
          console.log(index);
          console.log(row)
        }
      },
      mounted() {

      }
    }
</script>

<style scoped>

</style>
