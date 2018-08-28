/*
RequestService.js

class RequestService {

// async function
async getRequest(url){
  let data = await (await (fetch(url)
    .then(res => {
      return res.json()
    })
    .catch(err => {
      console.log('Error: ', err)
    })
  ))
  return data
}
}

export default new RequestService()
NetwrokService.js

import RequestService from './RequestService'

const BASE_URL = "https://api.nytimes.com/svc"
const API_KEY = "?api-key=GET-YOUR-FREE-API-KEY:)"

class NetworkService {

  getArticles(){
    var url = `${BASE_URL}/search/v2/articlesearch.json${API_KEY}`
    return RequestService.getRequest(url)
  }

  getTopStories(){
    var url = `${BASE_URL}/topstories/v2/technology.json${API_KEY}`
    return RequestService.getRequest(url)
  }

}

export default new NetworkService()
ArticlesMain.js


_getDataStories(){
    NetworkService.getTopStories()
      .then(data => {
        console.log('Data:', data)
        this.setState({
          albumList: data.results
        })
      })
  }

  componentDidMount(){
     this._getDataStories()
  }

....
....*/