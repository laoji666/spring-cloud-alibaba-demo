<template>
  <el-form ref="form"
           v-loading="formLoading"
           element-loading-text="加载中..."
           label-width="120px">
    <el-checkbox-group v-model="checkList">
      <template v-for="item in roleList">
        <el-checkbox :label="item.id">{{item.name}}</el-checkbox>
      </template>
    </el-checkbox-group>
    <el-button type="success" @click="submit">提交</el-button>
  </el-form>
</template>

<script>
  import {getRoleList,getRoleByAdminId,updateRole} from '@/api/system'
    export default {
        name: "role",
      data(){
          return{
            formLoading:true,
            id:0,
            checkList:[],
            roleList:[],
          }
      },
      created() {
          this.id=this.$route.query.id;
        this.fetchData()
      },
      methods:{
        fetchData() {
          getRoleList().then(response => {
            this.roleList = response.data
          });
          getRoleByAdminId({id:this.id}).then(response => {
            console.log()
            this.checkList=response.data;
            this.formLoading=false;
          });
        },
        submit(){
          this.formLoading=true;
          updateRole(this.checkList,this.id).then(response => {
            console.log(response)
            this.$message({
              message: response.message,
              type: 'success'
            })
            this.formLoading=false;
          }).catch(() => {
            this.formLoading=false;
          });
          console.log(this.checkList);
        }
      }
    }
</script>

<style scoped>

</style>
