import axios from 'axios'

let base = ''
export const postRequest = (url, params) => {
  return axios({
    method: 'post',
    url: `${base}${url}`,
    data: params,
    transformRequest: [function (data) {
      // Do whatever you want to transform the data
      let newData= ''
      for (let k in data) {
        newData+= encodeURIComponent(k) + '=' + encodeURIComponent(data[k]) + '&'
      }
      return newData
    }],
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    }
  })
}
