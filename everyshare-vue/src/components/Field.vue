<template>
  <div class="field-container">
    <div class="field">
      <div class="fieldHeader">
        <span>{{name}} </span>
      </div>
      <div class="fieldContent">
        <span>{{value}}</span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ['name', 'value']
}
</script>

<style>
  .field {
    display: flex;
    width: 510px;
    /* height: 60px; */
    border-bottom: 1px dashed #ccc;
  }
  .fieldHeader {
    flex: 1;
    display: inline-block;
    line-height: 30px;
    margin: 0 10px;
  }
  .fieldHeader span{
    color: gray;
    font-size: 16px;
    font-weight: bold;
  }
  .fieldContent {
    flex: 1;
    display: inline-block;
    margin: 5px 10px;
  }
  .fieldShow {
    position: relative;
    flex: 1;
    display: inline-block;
    width: 200px;
    height: 40px;
    line-height: 35px;
    margin: 0 10px;
  }
</style>
