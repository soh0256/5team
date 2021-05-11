module.exports = {
  secret:
    process.env.NODE_ENV === "production" ? process.env.SECRET : "backend!@",
  db: {
    user: "REACT",
    password: "REACT",
    connectSttring: "192.168.0.6/orcl",
  },
};
