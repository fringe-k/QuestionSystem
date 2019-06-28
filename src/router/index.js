import Vue from 'vue'
import Router from 'vue-router'
import QuestionShow from '@/components/QuestionShow'
import QuestionDetail from '@/components/QuestionDetail'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'QuestionShow',
      component: QuestionShow
    },
    {
      path: '/QuestionDetail',
      name: 'QuestionDetail',
      component: QuestionDetail
    }
  ]
})
