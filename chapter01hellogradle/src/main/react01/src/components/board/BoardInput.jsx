import React, { useState } from 'react';

import style from '../../css/Board.module.css'
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const BoardInput = () => {
    const [item, setItem] = useState({
        seq:0,
        name: '',
        subject: '',
        content: '',
        logtime: ''
    });

    const [div, setDiv] = useState('');
    const navi = useNavigate();

    const onInput = (e) => {
        setDiv('');
        switch (e.target.name) {
            case 'name':
                setItem({
                    ...item,
                    'name': e.target.value
                })
                break;
            case 'subject':
                    setItem({
                        ...item,
                        'subject': e.target.value
                    })
                    break;
            case 'content':
                setItem({
                    ...item,
                    'content': e.target.value
                })
                break;
            default:
                break;
        }
    }

    const onBoardSubmit = (e) => {
        var isOk = true;
        e.preventDefault();

        if(item.name === '' || item.content === '' || item.subject === '') isOk = false;

        if(!isOk) setDiv('필수 입력 사항을 확인하세요.');
        else { // Server -> Client
            axios.post('http://localhost:9000/react/board/submit', 
                item
            ).then(res => alert('게시글이 성공적으로 작성되었습니다.'))
            .catch(e => alert(e))

            console.log(item);
            navi('/board/list');
        }
    }

    return (
        <table className={style.table} id={style.wrtie}>
            <thead>
                <tr>
                    <td colSpan={2}>
                        게시글 작성
                    </td>
                </tr>
            </thead>
            <tbody>
            <tr>
                <th className={style.inputTh}>이름</th>
                    <td className={style.inputTd}><input type="text" name="name" id="" value={item.name} onChange={onInput}/></td>
                </tr>
                <tr>
                    <th className={style.inputTh}>제목</th>
                    <td className={style.inputTd}><input type="text" name="subject" id="" value={item.subject} onChange={onInput}/></td>
                </tr>
                <tr>
                    <th className={style.inputTh}>내용</th>
                    <td className={style.inputTd}>
                        <textarea name="content" id="" rows={20} cols={50} value={item.content} onChange={onInput}></textarea>
                    </td>
                </tr>
            </tbody>
            <tfoot>
                <tr>
                    <td colSpan={2}>
                        <div style={{color: 'red', fontSize: '12px'}}>{div}</div>
                        <button className={style.btn} onClick={onBoardSubmit}>작성</button>
                    </td>
                </tr>
            </tfoot>
        </table>
    );
};

export default BoardInput;