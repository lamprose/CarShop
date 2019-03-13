<template>
    <div>
      <el-dialog
        title="提示"
        :visible.sync="props.show"
        width="30%"
        :show-close="false"
        center>
        <div style="width: 70px;margin:30px auto"><label style="width: 70px;margin: auto">确认注销？</label></div>
        <span slot="footer" class="dialog-footer">
        <el-button @click="props.show = false">取 消</el-button>
        <el-button type="danger" @click="logout">确 定</el-button>
      </span>
      </el-dialog>
    </div>
</template>

<script>
    export default {
      name: "LogoutConfirm",
      props:['props'],
      methods:{
        logout(){
          //TODO:实现注销功能
          this.$store.dispatch("LogOut");
          this.props.show=false;
        }
      }
    }
</script>

<style scoped>

</style>
