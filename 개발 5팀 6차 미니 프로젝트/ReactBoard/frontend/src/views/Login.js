import React from 'react'
import { Link,Redirect} from 'react-router-dom'
import { Form, Button } from 'react-bootstrap'
import "@rmwc/button/styles"; // React Material Button 디자인 CSS 입니다.
import NaverLogin from 'react-login-by-naver'

import axios from 'axios'
import auth from '../auth' // 인증

export default class Login extends React.Component {
	constructor(props) {
		super(props)

		auth.default() // 기본 url 업데이트
		this.state = {
			id: null,
			password: null,
			loggedIn: false,
		}
	}
	
	login = async (event) => {
		event.preventDefault() // form 이벤트 멈추기
		const { id, password } = this.state // 데이터 불러옴
		
		// 서버에 로그인정보 전달
		axios.post('/user/login', { id, password })
			.then(res => {
				auth.setAuth(res.data) // 인증정보 저장
				this.setState({ loggedIn: true }) // 로그인정보 업데이트
			})
			.catch(err => alert(err.response.data.msg)) // 실패 시 응답 메시지 알림

	}

	// 네이버 로그인
	responseLogin = async(event) => {	
		const { id, password } = this.state // 데이터 불러옴
		
		// 서버에 로그인정보 전달
		axios.post('/user/naverlogin', { id, password })
			.then(res => {
				auth.setAuth(res.data) // 인증정보 저장
				this.setState({ loggedIn: true }) // 로그인정보 업데이트
			})
			.catch(err => alert(err.response.data.msg)) // 실패 시 응답 메시지 알림
	}
	
	render() {
		const { loggedIn } = this.state // 로그인정보 불러옴
		// 로그인 시 /로 리다이렉트
		if(loggedIn) return <Redirect to='/'/>
		// 아닐경우 폼 렌더링
		return (<>
			<Form onSubmit={this.login}>
				<Form.Group>
					<Form.Label>아이디</Form.Label>
					<Form.Control type='text' onChange={(event) => this.setState({ id: event.target.value })}/>
				</Form.Group>

				<Form.Group>
					<Form.Label>비밀번호</Form.Label>
					<Form.Control type='password'  onChange={(event) => this.setState({ password: event.target.value })}/>
				</Form.Group>

				<Button type='submit'>로그인</Button>
			</Form>

			<Link to="/register">
				<Button>회원가입</Button>
        	</Link>
			
			<br/>
	
			<NaverLogin
          		clientId="edEf7BinacuDsjWZNbw9"
				  callbackUrl="http://172.30.1.3:3010/management-callback"
          		  render={(props) => <div onClick={props.onClick}><img src="/naver-login.PNG" /></div>}
          		  onSuccess={(res) => {this.responseLogin(res, "naver")}}
          		  onFailure={() => console.log("naver login fail")}
        	/>
		
		</>)
	}
}