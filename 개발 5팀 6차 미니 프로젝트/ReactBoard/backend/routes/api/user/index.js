const router = require('express').Router()
const controller = require('./controllers')
cert = require("../../certification");


// 이미지 업로드 모듈
const multer = require("multer");
const upload = multer({ dest: "uploads/" });

// [POST] /login 요청시 호출
router.post('/login', controller.login)

router.post('/naverlogin', controller.naverlogin)

router.post("/register", cert.required, upload.single("profile"), controller.register);

module.exports = router
