<template>
<el-container>
  
     <el-aside>
        <el-menu default-active="0">
            <el-menu-item index="0" @click="handleCategoryClick(0)">
              全部
            </el-menu-item>
            <el-menu-item v-for="item in category" :key="item.id" :index="item.id" @click="handleCategoryClick(item.id)">
             {{item.name}}
            </el-menu-item>
        </el-menu>
      </el-aside>
      <el-container>
        <el-header >
      <el-row style="padding-top:10px">
      <el-col :span="5">
        <el-input placeholder="请输入关键词" v-model="keyword" clearable>
        </el-input>
        </el-col>
        <el-col :span="2">
        <el-button
          icon="el-icon-search"
          circle
          @click="getGoods"
        ></el-button>
      </el-col>
      </el-row>
    </el-header>
        <el-main>
        <el-row :gutter="10">
      <el-col :span="6" v-for="item in goods" :key="item.id">
        <result-card :item="item"></result-card>
      </el-col>
      <el-col>
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="page"
          :page-sizes= pageSizes
          :page-size="size"
          layout="total,sizes,prev, pager, next, jumper"
          :total="total"
        >
        </el-pagination>
      </el-col>
    </el-row>
      </el-main>
      </el-container>
      

</el-container>
</template>

<script>
import ResultCard from '@/components/ResultCard.vue';
export default {
  components: { ResultCard },
  name: "Index",
  data() {
    return {
      category:[],
      categoryId:0,
      goods:[],
      keyword:null,
      page:1,
      pageSizes: [1,10,100],
      size: 1,
      total: 0
    };
  },
  mounted() {
    this.getCategoryList()
    this.getGoods()
  },
  methods: {
    getCategoryList(){
      this.$http
        .get("/api/category/getAll")
        .then((res) => {
          console.log(res.data);
          this.category = res.data.data;
        });
    },
    getGoods(){
      this.$http
        .get("/api/goods/get/count", {
          params: { categoryId: this.categoryId,keyword:this.keyword },
        })
        .then((res) => {
          console.log(res);
          this.total = res.data.data;
        });
      this.$http
        .get("/api/goods/get",{
          params: { categoryId: this.categoryId,keyword:this.keyword,page:this.page,size:this.size },
        })
        .then((res) => {
          this.goods = res.data.data;
        });
    },
    handleCategoryClick(categoryId){
      this.categoryId = categoryId
      this.getGoods()
    },
    handleCurrentChange(val) {
      this.page = val;
      this.getGoods();
    },
    handleSizeChange(val) {
      this.size = val;
      this.getGoods();
    }
  }
};
</script>

<style scoped>
* {
  line-height: 16px;
}
.container {
  margin: auto;
  width: 1000px;
}

.el-col {
  border-radius: 4px;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}

.el-card {
  /*height: 240px;*/
  margin: 10px;
}
.el-card__body {
  height: 120px !important;
}
.el-main {
  background: #e9eef3;
}
.el-menu-item{
  padding-top: 20px;
}

</style>
