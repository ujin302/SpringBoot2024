import './App.css';
import './css/style.css'

import {BrowserRouter, Link, Route, Routes} from 'react-router-dom'
import Main from './components/main/Main';
import BoardList from './components/board/BoardList';
import BoardInput from './components/board/BoardInput';
import axios from 'axios';
import { useEffect, useState } from 'react';

function App() {
  // const [d, setD] = useState('');

  useEffect(() => {
    axios.delete(`http://localhost:9000/react/board/delete`, {
        params: { seq: 1 }
    })
    .then(res => console.log(res.data))
    .catch(error => console.error("Error:", error));

  }, []);

  return (
    <BrowserRouter>
      <>
        <nav className='menunav'>
          <ul>
            <li><Link to='/'>메인화면</Link></li>
            <li><Link to='/board/list'>게시물</Link></li>
          </ul>
        </nav>
        

        <div className='main'>
          <Routes>
            <Route path='/' element={ <Main/> }></Route>
            <Route path='/board/list' element={ <BoardList/> }/>
            <Route path='/board/submit' element={ <BoardInput/> }/>
          </Routes>
        </div>
      </>
    </BrowserRouter>
  );
}

export default App;
