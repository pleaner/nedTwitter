let principal;
const textarea = document.getElementById("textarea1");
const feedDiv = document.getElementById("feed_div");
const usernameDiv = document.getElementById("username");


let onPostSubmit = () => {
    const data = {
        author: principal.name,
        tweetContent: textarea.valueOf().value,
        date: Date.now(),
        likedBy: []
    };

    fetch('/api/tweets', {
        method: 'POST', // or 'PUT'
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    })
        .then(response => response.json())
        .then(data => {
            console.log('Success:', data);
        })
        .then(()=>loadTweets())
        .catch((error) => {
            console.error('Error:', error);
        });
}

window.onload = () => {

    loadTweets();
    whoami();
}

const loadTweets = () => {
    fetch("/api/tweets")
        .then(res => (
            res.json().then( data => {
                console.log(data)
                let newHTML = "";
                data.forEach(tweet => newHTML += tweetCard(tweet))
                feedDiv.innerHTML = newHTML;
            })
        ))
};

const tweetCard = (tweet) => `
        <div class="row">
            <div class="col s12">
                <div class="card white darken-1">
                    <div class="card-content blue-grey-text">
                        <span class="card-title">${tweet.author}</span>
                        <p>${tweet.tweetContent}</p>
                    </div>
                    <div class="card-action">
                        <a href=""><i class="material-icons">star_border</i>
                        <span class="new badge" data-badge-caption="Stars">${tweet.likedBy.length}</span></a>
                        ${tweet.author == principal.name ? 
                            `<a href=""><i class="material-icons" onclick="deleteTweet('${tweet.id}')">delete</i></a>`:""    
                         }
                    </div>
                </div>
            </div>
        </div>
`


const deleteTweet = id => {
    console.log("DELETE : ", id)
    fetch(`/api/tweets/${id}`, {method: 'DELETE'})
        .then(() => loadTweets())

}


const whoami = () => {
    fetch(`/api/tweets/whoami`)
        .then(res  => res.json())
        .then(data => {
            console.log(data)
            principal = data
            usernameDiv.innerHTML = data.name
        })
}


