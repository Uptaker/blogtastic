import './shared/ArrayExtensions'
import sinon from 'sinon'
import sinonChai from 'sinon-chai'
import chai from 'chai'
import {user} from 'src/stores/auth'
import type {User} from 'src/api/types'
import {Role} from 'src/api/types'

chai.use(sinonChai)
global.fetch = window.fetch = () => new Promise(() => {})
global.scrollTo = window.scrollTo = () => {}

beforeEach(() => {
  user.set({
    id: 'admin-id',
    firstName: 'Test',
    lastName: 'Admin',
    email: 'admin@codeborne.com',
    role: Role.ADMIN
  } as User)
})

afterEach(() => {
  sinon.restore()
  vi.restoreAllMocks()
})
