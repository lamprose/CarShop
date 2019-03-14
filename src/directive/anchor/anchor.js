import {goToElement} from '@/utils'
export default {
  inserted : function(el,binding){
    el.onclick = function(){
      goToElement(document.getElementById('anchor-'+binding.value).offsetTop)
    }
  }
}
