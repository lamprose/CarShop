<template>
    <div class="m-main">
      <div class="m-userinfo">
        <div class="s-bar" style="padding-top: 10px;">
          <a href="//i.taobao.com/user/baseInfoSet.htm?nekot=1554281062332&amp;tracelog=newmytb_bianjiziliao" class="s-avatar">
            <span class="s-edit-mask">编辑资料</span>
            <img src="//wwc.alicdn.com/avatar/getAvatar.do?userId=2910931509&amp;width=160&amp;height=160&amp;type=sns">
          </a>
          <input id="mtb-nickname" type="hidden" value="回忆总究为梦">
          <input id="mtb-userid" type="hidden" value="2910931509">
          <div class="s-name" style="float: left">
            <a href="//i.taobao.com/user/baseInfoSet.htm?spm=a310q.2219005.0.0.ncBmVr">
              <em>离墨一</em>
              (回忆总究为梦)
            </a>
          </div><br>
        </div>
        <div class="s-baseinfo">
          <ul style="display: block">
            <li class="s-item">
              <a href="//buyertrade.taobao.com/trade/itemlist/list_bought_items.htm?action=itemlist/BoughtQueryAction&amp;event_submit_do_query=1&amp;tabCode=waitPay"><span>待付款</span></a>
            </li>
            <li class="s-item">
              <a href="//buyertrade.taobao.com/trade/itemlist/list_bought_items.htm?action=itemlist/BoughtQueryAction&amp;event_submit_do_query=1&amp;tabCode=waitSend"><span>待发货</span></a>
            </li>
            <li class="s-item">
              <a href="//buyertrade.taobao.com/trade/itemlist/list_bought_items.htm?action=itemlist/BoughtQueryAction&amp;event_submit_do_query=1&amp;tabCode=waitConfirm"><span>待收货<em>1</em></span></a>
            </li>
            <li class="s-item">
              <a href="//buyertrade.taobao.com/trade/itemlist/list_bought_items.htm?action=itemlist/BoughtQueryAction&amp;event_submit_do_query=1&amp;tabCode=waitRate"><span>待评价<em>3</em></span></a>
            </li>
          </ul>
        </div>
      </div>
      <div class="m-carryinfo">
        <div class="s-bar">
          <svg-icon icon-class="shopping-cart"></svg-icon>
          <label>我的订单</label>
        </div>
      </div>
    </div>
</template>

<script>
    export default {
        name: "index"
    }
</script>

<style lang="less" scoped>
  .s-item{
    float: left;
    margin-left: 10px;
    margin-right: 10px;
  }
  .s-bar{
    position: relative;
    color: #546d7e;
    padding: 10px;
    line-height: 23px;
    height: 40px;
  }
  .m-main{
    border: 1px solid #e4eaee;
    .m-userinfo {
      margin-left: 20%;
      margin-right: 20%;
      background-color: #d0cdc7;
      border-radius: 10px;
      margin-bottom: 23px;
      font-size: 12px;
      color: #4b4b4b;
      position: relative;
      .s-bar{

      }
      .s-avatar {
        float: left;
        display: block;
        width: 52px;
        height: 52px;
        margin-right: 13px;
        margin-left: 12px;
        position: relative;
        border: 2px solid #FFF;
        border-radius: 50%;
        overflow: hidden;
        .s-edit-mask {
          line-height: 1;
          padding-top: 23px;
          display: none;
          width: 100%;
          height: 34px;
          position: absolute;
          text-align: center;
          background: #000;
          color: #fff;
          opacity: .6;
          filter: alpha(opacity=60);
        }
        &:hover{
          span{
            display: inline;
          }
        }
        img{
          border-radius: 50%;
          width: 100%;
          height: 100%;
        }
      }
    }
  }

</style>
