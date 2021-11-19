<template>
  <div>
    <a-tabs defaultActiveKey="1" size="large" style="padding: 20px 20px;">
      <a-tab-pane key="1" tab="创建用户">
        <a-card class="sysUser-card" :hoverable="true" title="创建用户">
          <div class="createSysUser">
            <a-form>
              <a-form-item>
                <a-input size="large" placeholder="请输入用户名" v-model="username">
                </a-input>
              </a-form-item>
              <a-form-item>
                <a-input-password size="large" placeholder="请输入密码" v-model="password">
                </a-input-password>
              </a-form-item>
              <a-form-item>
                <a-select size="large" placeholder="请选择用户状态" @change="getStatus">
                  <a-select-option value="0">正常</a-select-option>
                  <a-select-option value="1">冻结</a-select-option>
                </a-select>
              </a-form-item>
              <a-form-item>
                <a-select size="large" placeholder="请选择角色" @change="getRole">
                  <a-select-option :key="index" v-for="(item,index) in this.roleList" :value="item.roleName" >{{item.roleName}}</a-select-option>
                </a-select>
              </a-form-item>
              <a-form-item>
                <a-button style="width: 100%" type="primary" size="large" @click="createSysUser">创建用户</a-button>
              </a-form-item>
            </a-form>
          </div>
        </a-card>
      </a-tab-pane>
      <a-tab-pane key="2" tab="用户列表">
        <a-card class="sysUser-card" :hoverable="true" title="用户列表">

          <a-table :columns="columns" :data-source="sysUserInfo" rowKey="id" bordered :x-scroll="true">
          <span slot="action" slot-scope="text,record">
            <a-dropdown><a>切换角色<a-icon type="down"/></a>
             <a-menu slot="overlay" v-if="roleList.length>0">
                <a-menu-item v-for="(item,index) in roleList" :key="index" @click="changeRole(record,item)">{{item.roleName}}</a-menu-item>
             </a-menu>
           </a-dropdown>
           <a-divider type="vertical"/>
             <a-popconfirm title="确定要冻结或解冻该用户吗?" ok-text="确定" cancel-text="取消" @confirm="() => freeze(record)">
                <a v-if="record.status==0">冻结</a>
                <a v-else>解冻</a>
           </a-popconfirm>
           <a-divider type="vertical"/>
           <a-popconfirm title="确定要删除吗?" ok-text="确定" cancel-text="取消" @confirm="() => deleteSysUser(record)">
              <a>删除</a>
           </a-popconfirm>
         </span>
          </a-table>
        </a-card>
      </a-tab-pane>
    </a-tabs>
  </div>
</template>

<script>
  import {getAction, postAction} from "../../api/manage";

    export default {
        name: "SysUserManage",
        data(){
          return {
            columns:[
              {
                title: '#',
                dataIndex: '',
                key: 'rowIndex',
                width: 80,
                align: "center",
                customRender: function (t, r, index) {
                  return parseInt(index) + 1;
                }
              },
              {
                title: "用户名称",
                dataIndex: "username",
                align: "center"
              },
              {
                title:"角色名称",
                dataIndex:"roleName",
                align:"center"
              },
              {
                title:"角色描述",
                dataIndex:"description",
                align:"center"
              },
              {
                title:"状态",
                dataIndex:"status",
                align:"center",
                customRender:function(t,r){
                  if(t==="0") return "正常"
                  return "冻结"
                }
              },
              {
                title:"创建日期",
                dataIndex:"createTime",
                align:"center",
                customRender:function (t,r) {
                  return t.slice(0,10)
                }
              },
              {
                title: '操作',
                dataIndex: 'action',
                fixed:"right",
                width:220,
                align:"center",
                scopedSlots: { customRender: 'action' }
              }
            ],
            sysUserInfo:[],
            username:"",
            password:"",
            status:"",
            roleName:"",
            roleList:[],
            url:{
              listSysUser:"/sys/listSysUserDto",
              createSysUser:"/sys/createSysUserDto",
              deleteSysUser:"/sys/deleteSysUserDto",
              editSysUser:"/sys/editSysUser",
              editSysUserRole:"/sys/editSysUserRole",
              listRole:"/sys/listRole",
            }
          }
        },
        mounted() {
          this.listSysUser();
          this.listRole();
        },
        methods:{
          listSysUser(){
            getAction(this.url.listSysUser).then((res)=>{
              this.sysUserInfo = res.data;
            })
          },
          createSysUser(){
            let sysUserRoleModel = {};
            sysUserRoleModel.username = this.username;
            sysUserRoleModel.password = this.password;
            sysUserRoleModel.status   = this.status;
            sysUserRoleModel.roleName = this.roleName;
            postAction(this.url.createSysUser,sysUserRoleModel).then((res)=>{
                alert(res.data.message);
                if(res.data.success) {
                  this.username = "";
                  this.password = "";
                  this.status = "";
                  this.roleName = "";
                }else{
                  this.username = "";
                }
                this.listSysUser();
            })
          },
          deleteSysUser(record){
            postAction(this.url.deleteSysUser,record).then((res)=>{
              if(res.data.success){
                alert(res.data.message);
                this.listSysUser();
              }else{
                alert(res.data.message);
              }
            })
          },
          listRole(){
            getAction(this.url.listRole).then((res) => {
              this.roleList = res.data;
            })
          },
          freeze(record){
            if(record.status==="1"){
              record.status = "0";
            }else{
              record.status = "1";
            }
            postAction(this.url.editSysUser,record).then((res)=>{
              if(res.data.success){
                alert(res.data.message);
                this.listSysUser();
              }else{
                alert(res.data.message);
              }
            })
          },
          getStatus(value){
            this.status = value;
          },
          getRole(value){
            this.roleName = value;
          },
          changeRole(record,item){
            if(item.roleName===record.roleName){
              alert("该用户已经是"+item.roleName+"角色了!")
              return;
            }
            let model = {};
            model.username = record.username;
            model.roleName = item.roleName;
            postAction(this.url.editSysUserRole,model).then((res)=>{
                alert(res.data.message);
                if(res.data.success){

                  this.listSysUser();
                }
            })
          }
        }
    }
</script>

<style lang="less" scoped>
  .createSysUser{
    width:40%
  }
  .sysUser-card{
     padding:10px 10px;
     margin: 10px 10px;
  }
</style>
