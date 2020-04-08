<template>
  <div class="app-container">
    <el-form ref="form" :model="form" label-width="120px">
      <el-form-item label="用户名">
        <el-input v-model="form.username" />
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  import {info} from '@/api/profile'

  export default {
    data() {
      return {
        form: {
          username:''
        }
      }
    },
    created() {
      this.fetchData()
    },
    methods: {
      fetchData() {
        this.listLoading = true
        info(this.$store.getters.name).then(response => {
          this.form = response.data.items
        })
      }
    }
  }
</script>
