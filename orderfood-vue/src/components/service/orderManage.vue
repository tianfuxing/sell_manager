<template>
  <div>
    <el-row>
      <el-col :span="12">
        <div>
          <span style="margin: 0px 5px 0px 0px">下单时间</span>
          <el-date-picker size="small" v-model="startTime"  type="date" value-format="yyyy-MM-dd">
          </el-date-picker>
          --
          <el-date-picker size="small" v-model="endTime"  type="date" value-format="yyyy-MM-dd">
          </el-date-picker>
        </div>
      </el-col>
      <el-col :span="12">
        <div>
          <el-button type="primary" icon="search" size="small" @click="getDataPage(1,pageSize,startTime,endTime)">查询</el-button>
        </div>
      </el-col>
    </el-row>
    <el-table :data="tableData" border style="width: 100%;margin-top: 20px;"
      :header-cell-style="{ background: 'rgb(242, 243, 244)', color: '#515a6e' }">
      <el-table-column fixed prop="id" label="ID" v-if="false">
      </el-table-column>
      <el-table-column prop="number" label="订单编号">
      </el-table-column>
      <el-table-column prop="totalnum" width="100px" label="菜品数量">
      </el-table-column>
      <el-table-column prop="totalmoney" width="100px" label="总金额">
      </el-table-column>
      <el-table-column prop="status" width="150px" label="订单状态">
        <template slot-scope="scope">
          <div v-if="scope.row.status=='已接单'">{{scope.row.status}}</div>
          <div v-if="scope.row.status=='订单取消'" style="color:red;">{{scope.row.status}}</div>
          <div v-if="scope.row.status=='订单完成'" style="color:blue;">{{scope.row.status}}</div>
        </template>
      </el-table-column>
      <el-table-column :formatter="dateFormat" prop="createtime" label="下单时间">
      </el-table-column>
      <el-table-column fixed="right" label="操作">
        <template slot-scope="scope">
          <el-button size="mini" @click="look(scope.row)">查看</el-button>
          <el-button v-if="scope.row.status=='已接单'" type="danger" size="mini" @click="del(scope.row)">取消订单</el-button>
          <el-button v-if="scope.row.status=='已接单'"  type="primary" size="mini" @click="update(scope.row)">结账</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div style="margin-top:20px;float:right;">
      <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :page-sizes="[5, 10, 15, 20]"
        :page-size="pageSize" :current-page="currentPage" background layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>
    </div>
    <div>
      <el-dialog style="text-align: center;" width="450px" id="orderManage" :title="title" :visible.sync="dialogFormVisible">
        <el-form ref="order">
          <div v-for="item in order.orderInfoList" style="margin-top:10px;height:40px;line-height:40px;">
            <div style="float:left;width:200px;">{{ item.name }}×{{ item.num }}</div>
            <div style="float:left;width:200px;">￥{{ item.totalprice }}</div>
          </div>
          <div style="margin-top:10px;height:40px;line-height:40px;font-weight: 600;">
            <div style="float:left;width:200px;">合计：</div>
            <div style="float:left;width:200px;">￥{{ order.totalmoney }}</div>
          </div>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false" :disabled="dialogInputButtonVisible">取 消</el-button>
          <el-button type="primary" @click="submit()" :disabled="dialogInputButtonVisible">确 定</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import formatDate from '../../utils/dateUtil.js';
export default {
  methods: {
    handleSizeChange(val) {
      this.pageSize = val;
      this.getDataPage(this.currentPage, this.pageSize);
    },
    handleCurrentChange(val) {
      this.currentPage = val;
      this.getDataPage(this.currentPage, this.pageSize);
    },
    update(row) {
      this.order.id = row.id;
      this.order.type = row.type;
      this.order.number = row.number;
      this.order.totalnum = row.totalnum;
      this.order.totalmoney = row.totalmoney;
      this.order.status = row.status;
      this.order.address = row.address;
      this.order.name = row.name;
      this.order.tel = row.tel;
      this.order.createtime = row.createtime;
      this.order.orderInfoList = row.orderInfoList;
      this.title = "结账";
      this.dialogFormVisible = true;
      this.dialogInputButtonVisible = false;
    },
    del(row) {
      this.order.id = row.id;
      this.order.type = row.type;
      this.order.number = row.number;
      this.order.totalnum = row.totalnum;
      this.order.totalmoney = row.totalmoney;
      this.order.status = row.status;
      this.order.address = row.address;
      this.order.name = row.name;
      this.order.tel = row.tel;
      this.order.createtime = row.createtime;
      this.order.orderInfoList = row.orderInfoList;
      this.title = "取消订单";
      this.dialogFormVisible = true;
      this.dialogInputButtonVisible = false;
    },
    look(row) {
      this.order.id = row.id;
      this.order.type = row.type;
      this.order.number = row.number;
      this.order.totalnum = row.totalnum;
      this.order.totalmoney = row.totalmoney;
      this.order.status = row.status;
      this.order.address = row.address;
      this.order.name = row.name;
      this.order.tel = row.tel;
      this.order.createtime = row.createtime;
      this.order.orderInfoList = row.orderInfoList;
      this.title = "查看订单";
      this.dialogFormVisible = true;
      this.dialogInputButtonVisible = true;
    },
    submit() {
      const that = this;
      if (this.title == "取消订单") {
        this.$http
          .post('/specific/orderCancel', that.order)
          .then(function (response) {
            if (response.data.code == 200) {
              that.$message({
                showClose: true,
                type: 'success',
                message: '操作成功'
              });
              that.getDataPage(that.currentPage, that.pageSize);
              that.dialogFormVisible = false;
            }
          })
      } else if (this.title == "结账") {
        this.$http
          .post('/specific/orderAccomplish', that.order)
          .then(function (response) {
            if (response.data.code == 200) {
              that.$message({
                showClose: true,
                type: 'success',
                message: '操作成功'
              });
              that.getDataPage(that.currentPage, that.pageSize);
              that.dialogFormVisible = false;
            }
          })
      }
    },
    getDataPage(currentPage, pageSize,startTime,endTime) {
      const that = this;
      var param = new URLSearchParams();
      param.append("page", currentPage);
      param.append("limit", pageSize);
      param.append("startTime", startTime);
      param.append("endTime", endTime);
      this.$http
        .get('/specific/getOrderByPage', {
          params: param
        })
        .then(function (response) {
          if (response.data.code == 200) {
            that.tableData = response.data.data;
            that.total = response.data.count;
          }
        })
    },
    //时间格式化 
    dateFormat(row, column) {
      let date = new Date(row[column.property]);
      return formatDate(date, 'yyyy-MM-dd hh:mm:ss');
    }
  },
  mounted() {
    this.getDataPage(this.currentPage, this.pageSize,this.startTime,this.endTime);
  },
  data() {
    return {
      order: {
        id: "",
        type: "",
        number: "",
        totalnum: "",
        totalmoney: "",
        status: "",
        address: "",
        name: "",
        tel: "",
        createtime: "",
        orderInfoList: [],
      },
      title: "",
      dialogInputButtonVisible: false,
      dialogFormVisible: false,
      total: 0,
      pageSize: 5,
      startTime:"",
      endTime:"",
      currentPage: 1,
      tableData: []
    }
  }
}
</script>
<style></style>
