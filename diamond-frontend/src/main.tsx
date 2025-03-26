import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
// import { BrowserRouter as Router, Switch, Route } from 'react-router-dom'
import './index.css'
//import { Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle } from './components/ui/card'
//import { Button } from './components/ui/button'
//import LoginCard from './components/login/Login'
import { Hero } from './components/ui/animated-hero'
//import App from './App.tsx'
// import { Button } from './components/ui/button.tsx'

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <div className='block'>
      <Hero/>
    </div>
  </StrictMode>,
)