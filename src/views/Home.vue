<template>
  <el-container style="min-height: 100vh">
    <el-container>
      <el-header style="font-size: 12px; border-bottom: 1px solid #ccc; line-height: 60px; display: flex">
        <div style="flex: 1; font-size: 20px">
          <span  style="cursor: pointer" @click="collapse"></span>
          <span><img src="../assets/logo.png" alt="" style="width: 40px; position: relative; top: 10px; right: 10px"><b style="text-align: center">社员管理系统</b></span>
        </div>
        <el-dropdown style="width: 70px; cursor: pointer">
          <span class="el-dropdown-link">王小虎</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
          <el-dropdown-menu slot="dropdown" >
            <el-dropdown-item style="font-size: 14px; padding: 10px 10px">个人信息</el-dropdown-item>
            <el-dropdown-item  @click.native="go('/login')">退出</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>

      </el-header>

      <el-main style="padding: 7px">
        <div style="margin: 10px 0">
          <el-input style="width: 200px" placeholder="请输入名称" suffix-icon="el-icon-search" v-model="name"></el-input>
<!--          <el-input style="width: 200px" placeholder="请输入邮箱" suffix-icon="el-icon-message" class="ml-5"></el-input>-->
<!--          <el-input style="width: 200px" placeholder="请输入地址" suffix-icon="el-icon-position" class="ml-5"></el-input>-->
          <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
          <el-button type="primary" @click="add" class="ml-5" style="width: 100px;font-size:12px"><b>新增</b></el-button>
        </div>

        <el-table :data="tableData"
                  max-height="644"
                  border stripe :header-cell-class-name="headerBg">
          <el-table-column prop="id" label="ID" width="80"></el-table-column>
          <el-table-column prop="name" label="用户名" width="160"></el-table-column>
          <el-table-column prop="ocupation" label="职位" width="240"></el-table-column>
          <el-table-column prop="email" label="邮箱" width="240"></el-table-column>
          <el-table-column prop="tel" label="电话" width="150"></el-table-column>
          <el-table-column prop="address" label="地址" width="auto"></el-table-column>
          <el-table-column label="操作"  width="260" align="center">
            <template slot-scope="scope">
              <el-button type="success" @click="midfy(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
              <el-button type="danger" @click="del(scope.row)">删除<i class="el-icon-remove-outline"></i></el-button>
            </template>
          </el-table-column>
        </el-table>
        <div style="padding: 10px 0">
          <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="pageNum"
              :page-sizes="[15, 40, 60, 100]"
              :page-size="pageSize"
              layout="total, sizes, prev, pager, next, jumper"
              :total="total">
          </el-pagination>
        </div>
      </el-main>

      <el-dialog
          title="【提示】"
          :visible.sync="Visible"
          width="30%"
          :close-on-click-modal="false"
          center>
        <span>你确定要删除这条数据吗？</span>
        <span slot="footer" class="dialog-footer">
    <el-button @click="Visible = false">取 消</el-button>
    <el-button type="primary" @click="delect">确 定</el-button>
  </span>
      </el-dialog>

      <el-dialog
          title="編集画面"
          :visible.sync="centerDialogVisible"
          :close-on-click-modal="false"
          center
      >
        <el-form :model="from"  label-width="120px" style="width: 85%">
          <el-form-item label="ID" prop="id">
            <el-input v-model="from.id" autocomplete="off" :disabled="true"/>
          </el-form-item>
          <el-form-item label="用户名" prop="name">
            <el-input v-model="from.name" autocomplete="off"/>
          </el-form-item>
          <el-form-item label="职位" prop="ocupation">
            <el-input v-model="from.ocupation" autocomplete="off"/>
          </el-form-item>
          <el-form-item label="邮箱" prop="email">
            <el-input v-model="from.email" autocomplete="off"/>
          </el-form-item>
          <el-form-item label="电话" prop="tel">
            <el-input v-model="from.tel" autocomplete="off"/>
          </el-form-item>
          <el-form-item label="地址" prop="address">
            <el-input v-model="from.address" autocomplete="off"/>
          </el-form-item>
        </el-form>
        <template #footer>
      <span class="dialog-footer">
        <el-button @click="centerDialogVisible=false">取り消し</el-button>
        <el-button type="primary" @click="save">確定</el-button>
      </span>
        </template>
      </el-dialog>


      <el-dialog
          title="新規追加画面"
          :visible.sync="dialogVisible"
          :close-on-click-modal="false"
          center
      >
        <el-form :model="from"  ref="ruleFormRef" label-width="120px" style="width: 85%">
          <el-form-item label="用户名" prop="name">
            <el-input v-model="from.name" autocomplete="off" placeholder="氏名を入力してください" />
          </el-form-item>
          <el-form-item label="职位" prop="ocupation">
            <el-input v-model="from.ocupation" autocomplete="off" placeholder="氏名を入力してください" />
          </el-form-item>
          <el-form-item label="邮箱" prop="email">
            <el-input v-model="from.email" autocomplete="off" placeholder="氏名を入力してください" />
          </el-form-item>
          <el-form-item label="电话" prop="tel">
            <el-input v-model="from.tel" autocomplete="off" placeholder="氏名を入力してください" />
          </el-form-item>
          <el-form-item label="地址" prop="address">
            <el-input v-model="from.address" autocomplete="off" placeholder="氏名を入力してください" />
          </el-form-item>
        </el-form>
        <template #footer>
      <span class="dialog-footer">
        <el-button @click="close">取り消し</el-button>
        <el-button type="primary" @click="save">確定</el-button>
      </span>
        </template>
      </el-dialog>

    </el-container>
  </el-container>
</template>

<script>
import { Dropdown, DropdownMenu, DropdownItem } from 'element-ui';
export default {
  components: {
    'el-dropdown': Dropdown,
    'el-dropdown-menu': DropdownMenu,
    'el-dropdown-item': DropdownItem
  },
  name: 'Home',
  data() {
    return {
      tableData: [],
      from:{},
      dialogData:{},
      total: 0,
      pageNum: 1,
      pageSize: 15,
      name: "",
      rules:[0],
      dialogVisible:false,
      centerDialogVisible:false,
      Visible:false,
      isCollapse: false,
      sideWidth: 200,
      logoTextShow: true,
      headerBg: 'headerBg'
    }
  },
  created() {
    // 请求分页查询数据
    this.load()
  },
  methods: {
    collapse() {  // 点击收缩按钮触发
      this.isCollapse = !this.isCollapse
      if (this.isCollapse) {  // 收缩
        this.sideWidth = 64
        this.logoTextShow = false
      } else {   // 展开
        this.sideWidth = 200
        this.logoTextShow = true
      }
    },
    go(path){
      console.log(this.$router)
      console.log("AAAAAAAAAA"+path)
      this.$router.push('/login')
      history.pushState(null, null, document.URL);
      window.addEventListener("popstate",function(e) {
        history.pushState(null, null, document.URL);

      }, false);
    },
    add(){
      this.dialogVisible=true
      this.from = {};
    },
    close(){
      this.dialogVisible=false
    },
    midfy(row){
      this.centerDialogVisible=true
      this.from = JSON.parse(JSON.stringify(row));
    },
    del(row){
      this.Visible = true
      this.from = JSON.parse(JSON.stringify(row));
    },
    load() {
      fetch("http://localhost:9090/page?pageNum="+this.pageNum+"&pageSize=" + this.pageSize +"&name="+this.name)
          .then(res => res.json())
          .then(res => {
        this.tableData = res.data
        this.total = res.total
      })
    },
    delect(){
      fetch("http://localhost:9090/del/"+this.from.id)
          .then(res => res.json())
          .then(res => {
            this.Visible=false
            this.load();
          })
    },
    save(){
      fetch("http://localhost:9090/add",{
        method:"post",
        headers:{
          "Content-Type":"application/json"
        },
        body:JSON.stringify({
          id:this.from.id,
          name:this.from.name,
          ocupation:this.from.ocupation,
          email:this.from.email,
          tel:this.from.tel,
          address:this.from.address
        }),
      }).then(res =>res.json())
          .then(res=>{
            this.dialogVisible=false
            this.centerDialogVisible=false
            this.Visible=false
            this.load()
          })
    },
    handleSizeChange(pageSize) {
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      this.pageNum = pageNum
      this.load()
    }
  }
}
</script>

<style>
.headerBg {
  background: #eee!important;
}
</style>
