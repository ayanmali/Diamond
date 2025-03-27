import { StrictMode } from 'react';
import { createRoot } from 'react-dom/client';
// import { BrowserRouter as Router, Switch, Route } from 'react-router-dom'
import './index.css';
//import { Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle } from './components/ui/card'
//import { Button } from './components/ui/button'
import LoginCard from './components/login/Login';
import { ThemeProvider } from './components/theme-provider';
import { LandingPage } from './components/lander/Lander';
import { BrowserRouter, Routes, Route } from 'react-router-dom';

//import App from './App.tsx'
// import { Button } from './components/ui/button.tsx'

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <ThemeProvider defaultTheme="dark" storageKey="vite-ui-theme">
      <div className='block'>
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<LandingPage/>}/>
            <Route path="/login" element={<LoginCard/>}/>
          </Routes>
        </BrowserRouter>
      </div>
    </ThemeProvider>
  </StrictMode>,
)