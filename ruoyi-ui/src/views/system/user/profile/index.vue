<template>
   <div class="app-container">
     <el-row :gutter="20">
       <el-col :span="6" :xs="24">
         <el-card class="box-card">
           <template v-slot:header>
             <div class="clearfix">
               <span v-if="state.user.userType === '02'">Merchant Info</span>
               <span v-else>Profile Info</span>
             </div>
           </template>
           <div>
             <div class="text-center">
               <userAvatar />
             </div>
             <ul class="list-group list-group-striped">
               <li class="list-group-item">
                 <svg-icon icon-class="user" /> Username
                 <div class="pull-right">{{ state.user.userName }}</div>
               </li>
               <li class="list-group-item">
                 <svg-icon icon-class="phone" /> Phone Number
                 <div class="pull-right">{{ state.user.phonenumber }}</div>
               </li>
               <li class="list-group-item">
                 <svg-icon icon-class="email" /> Email
                 <div class="pull-right">{{ state.user.email }}</div>
               </li>
               <li class="list-group-item">
                 <svg-icon icon-class="date" /> Created At
                 <div class="pull-right">{{ state.user.createTime }}</div>
               </li>
               <li v-if="state.user.userType === '02'">
                 <svg-icon icon-class="date" /> Business License
                 <div class="pull-right">
                   <image-preview :src="state.user.businessLicense" :width="200" :height="200"/>
                 </div>
               </li>
             </ul>
           </div>
         </el-card>
       </el-col>
       <el-col :span="18" :xs="24">
         <el-card>
           <template v-slot:header>
             <div class="clearfix">
               <span>Basic Information</span>
             </div>
           </template>
           <el-tabs v-model="activeTab">
             <el-tab-pane label="Profile Info" name="userinfo">
               <userInfo :user="state.user" />
             </el-tab-pane>
             <el-tab-pane label="Change Password" name="resetPwd">
               <resetPwd />
             </el-tab-pane>
           </el-tabs>
         </el-card>
       </el-col>
     </el-row>
   </div>
 </template> 

<script setup name="Profile">
import userAvatar from "./userAvatar";
import userInfo from "./userInfo";
import resetPwd from "./resetPwd";
import { getUserProfile } from "@/api/system/user";

const activeTab = ref("userinfo");
const state = reactive({
  user: {},
  roleGroup: {},
  postGroup: {}
});

function getUser() {
  getUserProfile().then(response => {
    state.user = response.data;
    state.roleGroup = response.roleGroup;
    state.postGroup = response.postGroup;
  });
};

getUser();
</script>
