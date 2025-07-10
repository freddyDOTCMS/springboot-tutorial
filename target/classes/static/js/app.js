
const postList = document.getElementById("post-list");
const viewPosts = document.getElementById("view-posts");
const viewDetail = document.getElementById("view-post-detail");
const viewCreate = document.getElementById("view-create-post");
const viewCreateAuthor = document.getElementById("view-create-author");

function showPostList() {
    viewPosts.style.display = "block";
    viewDetail.style.display = "none";
    viewCreate.style.display = "none";
    viewCreateAuthor.style.display = "none";
    loadPosts();
}

let currentPostId = null;

function showPostDetail(id) {
    currentPostId = id;
    fetch(`/api/posts/${id}`)
        .then(res => res.json())
        .then(post => {
            document.getElementById("detail-title").textContent = post.title;
            document.getElementById("detail-content").textContent = post.content;
            viewPosts.style.display = "none";
            viewDetail.style.display = "block";

            const commentList = document.getElementById("comment-list");
            commentList.innerHTML = "";
            post.comments.forEach(c => {
                const li = document.createElement("li");
                li.textContent = c.text;
                commentList.appendChild(li);
            });
        });
}


function showCreateForm() {
    console.log('viewPosts', viewPosts, document.getElementById("post-list"));
    console.log('viewCreate', viewCreate);
    viewPosts.style.display = "none";
    viewCreate.style.display = "block";
    viewCreateAuthor.style.display = "none";
    loadAuthors();
}


function loadPosts() {
    fetch("/api/posts")
        .then(res => res.json())
        .then(posts => {
            postList.innerHTML = ""; // Clear before reloading
            posts.forEach(post => {
                const box = document.createElement("div");
                box.className = "post-box";
                box.innerHTML = `
                <h3>${post.title}</h3>
                <p>${post.content.substring(0, 100)}...</p>
                <p>Author ${post.author.fullName}</p>
                <button onclick="showPostDetail(${post.id})">View Details</button>
            `;
                postList.appendChild(box);
            });
        });
}

function loadAuthors() {
    fetch('/api/authors')
        .then(res => res.json())
        .then(authors => {
            const select = document.getElementById('author-select');
            select.innerHTML = '<option value="">Select an author</option>';
            authors.forEach(author => {
                const option = document.createElement('option');
                option.value = author.id;
                option.textContent = author.fullName;
                select.appendChild(option);
            });
        });
}

document.getElementById("create-post-form").addEventListener("submit", function (e) {
    e.preventDefault();
    const formData = new FormData(e.target);

    const authorId = formData.get("authorId");

    const newPost = {
        title: formData.get("title"),
        content: formData.get("content"),
        excerpt: formData.get("excerpt"),
        authorId: parseInt(authorId)
    };

    fetch("/api/posts", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(newPost)
    }).then(() => {
        showPostList();
    });
});

document.getElementById("comment-form").addEventListener("submit", function (e) {
    e.preventDefault();
    const formData = new FormData(e.target);
    const comment = {
        text: formData.get("text")
    };

    fetch(`/api/posts/${currentPostId}/comments`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(comment)
    })
        .then(res => res.json())
        .then(() => {
            e.target.reset();         // Clear textarea
            showPostDetail(currentPostId); // Refresh comment list
        });
});

function showCreateAuthorForm() {
    viewPosts.style.display = "none";
    viewDetail.style.display = "none";
    viewCreate.style.display = "none";
    viewCreateAuthor.style.display = "block";
}

document.getElementById("create-author-form").addEventListener("submit", function (e) {
    e.preventDefault();
    const formData = new FormData(e.target);
    const author = {
        firstName: formData.get("firstName"),
        lastName: formData.get("lastName"),
        email: formData.get("email")
    };

    fetch("/api/authors", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(author)
    })
        .then(res => res.json())
        .then(() => {
            e.target.reset();
            alert("Author created successfully!");
            showPostList();
        });
});


// All your JS logic here, including event listeners
loadPosts();
