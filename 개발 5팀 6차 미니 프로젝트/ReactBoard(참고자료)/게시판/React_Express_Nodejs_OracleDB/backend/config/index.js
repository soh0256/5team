module.exports = {
	secret: process.env.NODE_ENV === 'production' ? process.env.SECRET : 'backend!@',
	db: {
		user: 'data',
		password: 'password',
		connectSttring: 'localhost/orcl'
	}
}
