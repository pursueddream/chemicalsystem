<template>
  <div>

    <div style="margin-top: 10px;">
        <Button  @click.native="add()">新增订单</Button>
    </div>

    <div style="margin-top: 10px">
      <Table stripe :columns="columns" :data="data"></Table>
    </div>

    <Modal
      v-model="orderCreate"
      title="创建订单"
      @on-ok="ok"
      @on-cancel="cancel">
      <Form  :label-width="80">
        <FormItem label="订单编号">
          <Input v-model="form.orderNo" placeholder="请输入订单编号" />
        </FormItem>
        <FormItem label="订单名称">
          <Input v-model="form.name" placeholder="请输入订单名称" />
        </FormItem>
        <FormItem label="类型">
          <Select v-model="form.type">
            <Option value="type_1">采购订单</Option>
            <Option value="type_2">销售订单</Option>
            <Option value="type_3">物流订单</Option>
          </Select>
        </FormItem>
      </Form>
    </Modal>
  </div>
</template>

<script>
  export default {
    data () {
      return {
        form : {
          orderNo : "",
          name : "",
          type : "type_1"
        },
        orderCreate: false,
        columns: [
          {
            title: '订单编号',
            key: 'orderNo'
          },
          {
            title: '订单名称',
            key: 'name'
          },
          {
            title: '订单类型',
            key: 'type'
          },
          {
            title : "操作",
            key : "action",
            render: (h, params) => {
              return h('div', [
                h('Button', {
                  props: {
                    type: 'text',
                    size: 'small'
                  }
                }, '编辑'),
                h('Button', {
                  props: {
                    type: 'text',
                    size: 'small'
                  }
                }, '删除')
              ]);
            }
          }
        ],
        data :[]
      }
    },
    methods: {
      add(){
        this.orderCreate = true;
      },
      ok(){
        const params = {
          orderNo : this.form.orderNo,
          name : this.form.name,
          type : this.form.type
        };
        this.$http.post("/order", params).then((res) => {
          //添加成功后， 刷新
          this.refreshTable();
        })
      },
      cancel(){},
      refreshTable(){
        const query = {
          pageNumber : 1,
          pageSize : 10
        };
        this.$http.get("/order", query).then((res) => {
          this.data = adapt(res.data.data);
        });
      }
    },
    mounted:function(){
      this.refreshTable();
    },
  }

  function adapt(data) {
    data.forEach(item => {
      item.type = typeAdapt(item.type)
    });
    return data;
  }

  function typeAdapt(type) {
    if (type === "type_1") {
      return "采购订单";
    }else if (type === "type_2") {
      return "销售订单";
    }else {
      return "物流订单";
    }
  }


</script>
