<template>
  <div class="app-container">
    <el-form ref="form"
             v-loading="formLoading"
             :data="form"
             element-loading-text="加载中..."
             :model="form"
             label-width="120px">
      <el-input type="hidden" v-model="form.id" />
      <el-form-item label="用户名">
        <el-input v-model="form.username" />
      </el-form-item>

      <el-form-item label="邮箱">
        <el-input v-model="form.email" />
      </el-form-item>
      <el-form-item label="昵称">
        <el-input v-model="form.nickName" />
      </el-form-item>
      <el-form-item label="备注">
        <el-input v-model="form.note" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">修改</el-button>
        <el-button @click="onCancel">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  import {info,update} from '@/api/profile'

  export default {
    data() {
      return {
        formLoading:true,
        form: {
          id:0,
          username:'',
          icon:'',
          email:'',
          nickName:'',
          note:'',
        }
      }
    },
    created() {
      this.fetchData()
    },
    methods: {
      fetchData() {
        info(this.$store.getters.name).then(response => {
          this.form = response.data
          this.formLoading = false
        })
      },
      onSubmit() {
        this.formLoading = true
        update(this.form).then(response => {
          this.formLoading = false
          this.$message({
            message: response.message,
            type: 'success'
          })
        }).catch(()=>{
          this.formLoading = false
        })
      },
      onCancel() {
        this.$message({
          message: 'cancel!',
          type: 'warning'
        })
      }
    }
  }
</script>
