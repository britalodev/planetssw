db.createUser(
        {
            user:"italo",
            pwd:"123456789",
            roles: [
                {
                    role:"readWrite",
                    db:"starwars"
                }
            ]
        }
);