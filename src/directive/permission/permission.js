
import store from '@/store'

export default{
  inserted(el, binding, vnode) {
    const { value } = binding
    if(value==='')
      return
    let role = store.getters && store.getters.role
    const tempRoles= store.getters && store.getters.tempRoles
    if(role==="superAdmin"||(tempRoles&&tempRoles instanceof String && tempRoles.length>0)){
      role="admin"
    }
    if (value && value instanceof Array && value.length > 0) {
        const permissionRoles = value

        const hasPermission = permissionRoles.some(roleItem => {
          return roleItem===role
        })

        if (!hasPermission) {
          el.parentNode && el.parentNode.removeChild(el)
        }

    }else {
      throw new Error(`need roles! Like v-permission="['admin','editor']"`)
    }
  }
}
