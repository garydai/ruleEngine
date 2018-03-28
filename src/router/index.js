import Vue from 'vue'
import Router from 'vue-router'

// in development-env not use lazy-loading, because lazy-loading too many pages will cause webpack hot update too slow. so only in production use lazy-loading;
// detail: https://panjiachen.github.io/vue-element-admin-site/#/lazy-loading

Vue.use(Router)

/* Layout */
import Layout from '../views/layout/Layout'

/**
* hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
* alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
*                                if not set alwaysShow, only more than one route under the children
*                                it will becomes nested mode, otherwise not show the root menu
* redirect: noredirect           if `redirect:noredirect` will no redirct in the breadcrumb
* name:'router-name'             the name is used by <keep-alive> (must set!!!)
* meta : {
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
  }
**/
export const constantRouterMap = [
  { path: '/login', component: () => import('@/views/login/index'), hidden: true },
  { path: '/404', component: () => import('@/views/404'), hidden: true },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    name: 'Dashboard',
    hidden: true,
    children: [{
      path: 'dashboard',
      component: () => import('@/views/dashboard/index')
    }]
  },
  {
    path: '/engine',
    component: Layout,
    redirect: '/engine/rule',
    name: 'engine',
    meta: { title: '规则引擎', icon: 'example' },
    children: [
      {
        path: 'scene',
        name: 'scene',
        component: () => import('@/views/rule/index'),
        meta: { title: '场景', icon: 'table' }
      },
      {
        path: 'rule',
        name: 'rule',
        hidden: true,
        component: () => import('@/views/rule/workflow'),
        meta: { title: 'rule' }
      },
      {
        path: 'test',
        name: 'test',
        component: () => import('@/views/rule/test'),
        meta: { title: '测试', icon: 'tree' }
      },
      {
        path: 'drl',
        name: 'drl',
        component: () => import('@/views/rule/drl'),
        hidden: true,
        meta: { title: 'drl' }
      },
      {
        path: 'version',
        name: 'version',
        component: () => import('@/views/rule/version'),
        meta: { title: '版本', icon: 'example' }
      }
    ]
  },
  {
    path: '/variables',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'variables',
        component: () => import('@/views/variables/index'),
        meta: { title: '变量', icon: 'form' }
      }
    ]
  },

  { path: '*', redirect: '/404', hidden: true }
]

export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})

