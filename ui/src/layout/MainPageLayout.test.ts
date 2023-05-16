import {render} from '@testing-library/svelte'
import MainPageLayout from 'src/layout/MainPageLayout.svelte'

describe('Main Page Layout', ()=> {
  it('renders title', async () =>{
    const {container} = render(MainPageLayout, {title: 'My Title'})
    expect(container.textContent).toContain('My Title')
  })
})
