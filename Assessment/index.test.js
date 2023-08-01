const supertest = require('supertest');
const app = require('./index.js');
const testData = require('./testData.json')

const request = supertest(app);

describe("API Tests:", () => {
    it("Calls each endpoint to make sure they're working", async () => {
        const res = await request.get('/api/ping');
        expect(res.statusCode).toEqual(200);

        const res2 = await request.get('/api/posts');
        expect(res2.statusCode).toEqual(400);
    });

    it("Sends tags to the /api/posts endpoint", async () => {
        const res = await request.get('/api/posts?tags=someTag,anothertag,finaltag')
        expect(res.statusCode).toEqual(200);
    });

    it("Sends a tag and a invalid sortBy to the /api/posts endpoint", async () => {
        const res = await request.get('/api/posts?tags=someTag&sortBy=what');
        expect(res.statusCode).toEqual(400);
    });

    it("Sends a tag and a valid sortBy to the /api/posts endpoint", async () => {
        const res = await request.get('/api/posts?tags=someTag&sortBy=popularity');
        expect(res.statusCode).toEqual(200);
    });

    it("Sends a tag and a valid direction to the /api/posts endpoint", async () => {
        const res = await request.get('/api/posts?tags=someTag&direction=desc');
        expect(res.statusCode).toEqual(200);
    });

    it("Sends a tag and a valid direction to the /api/posts endpoint", async () => {
        const res = await request.get('/api/posts?tags=someTag&direction=desc');
        expect(res.statusCode).toEqual(200);
    });

    it("Sends multiple tags to see if the endpoint is calling correctly", async () => {
        const res = await request.get('/api/posts?tags=tech,history');
        expect(res.statusCode).toEqual(200);
    });

    it("Tests to see whether sample data gathered by the solution API is the same as the result", async () => {
        const res = await request.get('/api/posts?tags=history,tech&sortBy=likes&direction=desc');  

        expect(res.body).toEqual(testData);
    });
});