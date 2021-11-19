<template>
  <div>
    <a-card class="role-card" title="当前角色" :hoverable="true">
      <div>角色名称</div>
      <div>角色描述</div>
      <div>权限列表</div>
    </a-card>
    <a-card class="role-card" title="创建角色" :hoverable="true">
      <div class="createRoleInfo">
        <a-input placeholder="请输入角色名称 (必填)" v-model="roleName" style="width:40%"></a-input>
        <a-input placeholder="请输入角色描述 (选填)" v-model="description" style="width:40%"></a-input>
        <a-button type="primary" @click="createRole">
          <a-icon type="plus"/>
          新增角色
        </a-button>
      </div>
    </a-card>
    <a-card class="role-card" title="角色列表" :hoverable="true">
      <a-table :columns="columns" :data-source="roleInfo" rowKey="id" bordered>
         <span slot="action" slot-scope="text,record">
           <span>编辑</span>
           <a-divider type="vertical"/>
           <span>详情</span>
           <a-divider type="vertical"/>
           <span @click="authorize(record)">授权</span>
           <a-divider type="vertical"/>
           <a-popconfirm title="确定要删除吗?" ok-text="确定" cancel-text="取消" @confirm="() => deleteRole(record)">
              <a>删除</a>
           </a-popconfirm>
         </span>
      </a-table>
    </a-card>

  </div>
</template>

<script>

  import {getAction, postAction} from "../../api/manage";

  export default {
    name: "RoleSetting",
    data() {
      return {
        roleInfo: [],
        roleName: "",
        description: "",
        columns: [
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
            title: "角色名称",
            dataIndex: "roleName",
            width: 160,
            align: "center"
          },
          {
            title: "角色描述",
            dataIndex: "description",
            align: "center"
          },
          {
            title: "操作",
            dataIndex: 'action',
            width: 220,
            align: "center",
            scopedSlots: {customRender: 'action'}
          }
        ],
        url: {
          createRole: "/sys/createRole",
          listRole: "/sys/listRole",
          deleteRole: "/sys/deleteRole"
        }
      }
    },
    mounted() {
      this.listRole();
    },
    methods: {
      createRole() {
        this.roleName = this.roleName.trim();
        if (this.roleName === "" || this.roleName === "undefined") {
          alert("角色名称不能为空");
          return;
        }
        postAction(this.url.createRole, {"roleName": this.roleName, "description": this.description}).then((res) => {
          alert(res.data.message);
          if (res.data.success) {
            this.roleName="";
            this.description="";
            this.listRole();
          }
        })
      },
      listRole() {
        getAction(this.url.listRole).then((res) => {
          this.roleInfo = res.data;
        })
      },
      deleteRole(record) {
        postAction(this.url.deleteRole, record).then((res) => {
          if(res.data.success){
            alert(res.data.message);
            this.listRole();
          }else{
            alert(res.data.message);
          }
        })
      }
    }
  }
</script>

<style lang="less" scoped>
  .role-card{
    padding:10px 10px;
    margin: 10px 10px;
  }
  .createRoleInfo {
    display: flex;
    flex-direction: row;
    padding: 10px 10px;
    justify-content: space-between;
  }
</style>
