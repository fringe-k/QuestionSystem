import Vue from 'vue'
import Router from 'vue-router'
import QuestionScience from '@/components/QuestionScience'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'QuestionScience',
      component: QuestionScience
    }
  ]
})
