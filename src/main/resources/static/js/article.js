const deleteButton = document.getElementById('delete-btn');
const modifyButton = document.getElementById('modify-btn');
const createButton = document.getElementById('create-btn');

if(deleteButton) {
    deleteButton.addEventListener("click", event => {
        let id = document.getElementById('article-id').value;

        fetch(`/api/articles/${id}`, {
            method: 'DELETE',
        })
            .then(() => {
                alert('삭제 완료');
                location.replace('/articles');
            });
    });
}

if(modifyButton) {
    modifyButton.addEventListener("click", event => {
        let params = new URLSearchParams(location.search);
        let id = params.get('id');

        fetch(`/api/articles/${id}`, {
            method: 'PUT',
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: document.getElementById('title').value,
                content: document.getElementById('content').value
            })
        })
            .then(response => {
                if(!response.ok) {
                    throw new Error("수정 실패");
                }
                return response.json();
            })
            .then(data => {
                alert('수정 완료');
                document.getElementById('title').value = data.title;
                document.getElementById('content').value = data.content;
                location.replace(`/articles/${id}`);
            })
            .catch(error => {
                console.error('Error:', error);
                alert("수정 중 오류 발생");
            })
    });
}

if(createButton) {
    createButton.addEventListener("click", (event) => {
        fetch("/api/articles", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                title: document.getElementById("title").value,
                content: document.getElementById("content").value,
            }),
        }).then(() => {
            alert("등록 완료");
            location.replace("/articles");
        })
    })
}