import json
from flask import Flask, jsonify, make_response, request

app = Flask(__name__)

@app.route('/')
def anotherFun():
    args = request.args

    return args

@app.route('/api')
def f():
    dictResponse = {}
    dictResponse["hello"] = {"hello": "wordl"}
    res = make_response(jsonify(dictResponse), 200)
    return res


if __name__ == "__main__":
    app.run(host='localhost', port=3000)
