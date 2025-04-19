<template>
  <div class="app-container">
    <h2>Shipping Address Management</h2>
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
            type="primary"
            plain
            icon="Plus"
            @click="handleAdd"
        >Add</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="success"
            plain
            icon="Edit"
            :disabled="single"
            @click="handleUpdate"
        >Edit</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
            type="danger"
            plain
            icon="Delete"
            :disabled="multiple"
            @click="handleDelete"
        >Delete</el-button>
      </el-col>
    </el-row>

    <el-table v-loading="loading" :data="MallAddressList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="Full Address" align="center" prop="address" />
      <el-table-column label="Recipient" align="center" prop="receiverName" />
      <el-table-column label="Phone Number" align="center" prop="phoneNumber" />
      <el-table-column label="Default" align="center" prop="isDefault">
        <template #default="scope">
          <dict-tag :options="mall_yes_no" :value="scope.row.isDefault"/>
        </template>
      </el-table-column>
      <el-table-column label="Updated Time" align="center" prop="updatedTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.updatedTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Actions" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" >Edit</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" >Delete</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
        v-show="total > 0"
        :total="total"
        v-model:page="queryParams.pageNum"
        v-model:limit="queryParams.pageSize"
        @pagination="getList"
    />

    <!-- Add or Edit Shipping Address Dialog -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="MallAddressRef" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="Full Address" prop="address">
          <el-input v-model="form.address" placeholder="Please enter the full address" />
        </el-form-item>
        <el-form-item label="Recipient" prop="receiverName">
          <el-input v-model="form.receiverName" placeholder="Please enter recipient name" />
        </el-form-item>
        <el-form-item label="Phone Number" prop="phoneNumber">
          <el-input v-model="form.phoneNumber" placeholder="Please enter phone number" />
        </el-form-item>
        <el-form-item label="Default" prop="isDefault">
          <el-radio-group v-model="form.isDefault">
            <el-radio
                v-for="dict in mall_yes_no"
                :key="dict.value"
                :label="parseInt(dict.value)"
            >{{ dict.label }}</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">Confirm</el-button>
          <el-button @click="cancel">Cancel</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>


<script setup name="MallAddress">
import { listMallAddress, getMallAddress, delMallAddress, addMallAddress, updateMallAddress } from "@/api/system/MallAddress";

const { proxy } = getCurrentInstance();
const { mall_yes_no } = proxy.useDict('mall_yes_no');

const MallAddressList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    userId: null,
    address: null,
    receiverName: null,
    phoneNumber: null,
    isDefault: null,
    createdTime: null,
    updatedTime: null
  },
  rules: {
    userId: [
      { required: true, message: "用户ID不能为空", trigger: "blur" }
    ],
    address: [
      { required: true, message: "详细地址不能为空", trigger: "blur" }
    ],
    receiverName: [
      { required: true, message: "收货人不能为空", trigger: "blur" }
    ],
    phoneNumber: [
      { required: true, message: "手机号不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询收货地址列表 */
function getList() {
  loading.value = true;
  listMallAddress(queryParams.value).then(response => {
    MallAddressList.value = response.rows;
    total.value = response.total;
    loading.value = false;
  });
}

// 取消按钮
function cancel() {
  open.value = false;
  reset();
}

// 表单重置
function reset() {
  form.value = {
    id: null,
    userId: null,
    address: null,
    receiverName: null,
    phoneNumber: null,
    isDefault: null,
    createdTime: null,
    updatedTime: null
  };
  proxy.resetForm("MallAddressRef");
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef");
  handleQuery();
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.id);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加收货地址";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getMallAddress(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改收货地址";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["MallAddressRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateMallAddress(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addMallAddress(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          getList();
        });
      }
    }
  });
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _ids = row.id || ids.value;
  proxy.$modal.confirm('是否确认删除收货地址编号为"' + _ids + '"的数据项？').then(function() {
    return delMallAddress(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('system/MallAddress/export', {
    ...queryParams.value
  }, `MallAddress_${new Date().getTime()}.xlsx`)
}

getList();
</script>
<style scoped>
.app-container {
  max-width: 1200px;
  margin: 0 auto;
}
</style>