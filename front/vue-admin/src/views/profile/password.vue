<template>
  <div class="app-container">
    <el-form ref="form"
             v-loading="formLoading"
             :data="form"
             element-loading-text="加载中..."
             :model="form"
             label-width="120px">
      <el-form-item label="原密码">
        <el-input v-model="form.oldPassword" type="password" />
      </el-form-item>
      <el-form-item label="新密码">
        <el-input v-model="form.newPassword" type="password" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">修改</el-button>
        <el-button @click="onCancel">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  import {updatePassword} from '@/api/profile'
  export default {
    data() {
      return {
        formLoading:false,
        form: {
          userName:this.$store.getters.name,
          oldPassword:'',
          newPassword:'',
        }
      }
    },
    created() {
      this.fetchData()
    },
    methods: {
      onSubmit(){
        this.formLoading = true
        if(this.form.newPassword.length<6){
          this.$message({
            message: "新密码必须大于等于六位数",
            type: 'error'
          })
          this.formLoading = false
          return;
        }
        updatePassword(this.form).then(response => {
          this.formLoading = false
          this.$message({
            message: response.message,
            type: 'success'
          })
        }).catch(()=>{
          this.formLoading = false
        })
      },
      onCancel(){

      }
    }
  }
</script>
