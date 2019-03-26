
import store from '@/store'

export default{
  inserted(el, binding, vnode) {
    const { value } = binding
    let roles = store.getters && store.getters.roles
    const tempRoles= store.getters && store.getters.tempRoles
    if(tempRoles&&tempRoles instanceof String && tempRoles.length>0){
      roles="admin"
    }
    if (value && value instanceof Array && value.length > 0) {
      const permissionRoles = value

      const hasPermission = permissionRoles.some(role => {
        return role===roles
      })

      if (!hasPermission) {
        el.parentNode && el.parentNode.removeChild(el)
      }
    } else {
      throw new Error(`need roles! Like v-permission="['admin','editor']"`)
    }
  }
}
