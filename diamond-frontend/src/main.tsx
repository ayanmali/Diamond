import { StrictMode, useState } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
//import App from './App.tsx'
import { Button } from './components/ui/button.tsx'

const App = () => {
  const [flag, setFlag] = useState(false);

  return (
  <div className='min-h-screen flex flex-col items-center justify-center'>
    <Button size={"lg"} onClick={() => setFlag(!flag)}>Click me</Button>
    {flag && <span>Appear</span>}
  </div>
  )
  
}

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <App />
    {/* <MyComp/> */}
  </StrictMode>,
)
