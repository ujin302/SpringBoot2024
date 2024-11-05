import React, { useEffect, useState } from 'react';

import style from '../../css/Board.module.css'
import axios from 'axios';
import { Link } from 'react-router-dom';

const BoardList = () => {
    const [list, setList] = useState([]);
    const [url, setUrl] = useState('/board/submit');

    useEffect(() => {
        axios.get('http://localhost:9000/react/board/list')
        .then(res => setList(res.data))

        console.log(list)

    }, [])
    
    const onSubmit = () => {
        setUrl('/board/submit');
    }

    return (
        <div>
            <table className={style.table} id={style.list}>
                <thead>
                    <tr>
                        <td colSpan={3}>
                            게시물 목록
                        </td>
                        <td>
                            <Link to={url} onClick={onSubmit}>게시글 작성</Link>
                        </td>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td className={style.seq}>번호</td>
                        <td className={style.name}>이름</td>
                        <td className={style.subject}>제목</td>
                        <td className={style.content}>내용</td>
                    </tr>
                    {
                        list.map(item => 
                            <tr>
                                <td className={style.seq}>{item.seq}</td>
                                <td className={style.name}>{item.name}</td>
                                <td className={style.subject}>
                                    {/* <Link to='/board/item' onClick={() => onSeq(item.seq)}> */}
                                        {item.subject}
                                    {/* </Link> */}
                                </td>
                                <td className={style.content}>{item.content}</td>
                            </tr>
                        )
                    }
                </tbody>
            </table>
        </div>
    );
};

export default BoardList;