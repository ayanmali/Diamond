import { StrictMode } from 'react';
import { createRoot } from 'react-dom/client';
// import { BrowserRouter as Router, Switch, Route } from 'react-router-dom'
import './index.css';
//import { Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle } from './components/ui/card'
//import { Button } from './components/ui/button'
//import LoginCard from './components/login/Login';
//import { ThemeProvider } from './components/theme-provider';
// import { LandingPage } from './components/lander/Lander';
// import { BrowserRouter, Routes, Route } from 'react-router-dom';
// import Dashboard from './components/dashboard/Dashboard';

//import App from './App.tsx'
// import { Button } from './components/ui/button.tsx'
import { Client } from '@stomp/stompjs';
import SockJS from 'sockjs-client';

let stompClient: Client;

function connect() {
  // Use SockJS endpoint
  const socket = new SockJS('http://localhost:8080/ws');
  stompClient = new Client({
    webSocketFactory: () => socket,
    reconnectDelay: 5000,
    onConnect: () => {
      console.log('Connected via STOMP');

      // Subscribe to the topic where you expect responses
      stompClient.subscribe('/topic/messages', (message) => {
        console.log('Received:', message.body);
      });
    }
  });

  stompClient.activate();
}

function disconnect() {
  if (stompClient) {
    stompClient.deactivate();
  }
  console.log("WebSocket STOMP connection disconnected.");
}

function send() {
  if (stompClient && stompClient.connected) {
    const payload = {
      data: 'hello',
      number: 69
    };
    console.log('Sending data via STOMP...');
    // Send to the destination associated with the controller's @MessageMapping
    stompClient.publish({
      destination: '/app/message',
      body: JSON.stringify(payload)
    });
  } else {
    console.log('STOMP client is not connected.');
  }
}


// createRoot(document.getElementById('root')!).render(
//   <StrictMode>
//     <ThemeProvider defaultTheme="dark" storageKey="vite-ui-theme">
//       <div className='block'>
//         <BrowserRouter>
//           <Routes>
//             <Route path="/" element={<LandingPage/>}/>
//             <Route path="/login" element={<LoginCard/>}/>
//             <Route path="/dashboard" element={<Dashboard/>}/>
//           </Routes>
//         </BrowserRouter>
//       </div>
//     </ThemeProvider>
//   </StrictMode>,
// )

createRoot(document.getElementById('root')!).render(
<StrictMode>
<div>
  <button onClick={connect}>Connect to WebSocket</button>
</div>
      
<div>
  <button onClick={disconnect}>Disconnect from WebSocket</button>
</div>

<div>
  <button onClick={send}>Send Data</button>
</div>
</StrictMode>
)