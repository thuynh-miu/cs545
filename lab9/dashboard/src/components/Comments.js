const Comments = (props) => {
    const commentsList = props.comments.map(comment => {
        return (
            <div key={comment.id}>
                <p>{comment.name}</p>
            </div>
        )
    })

    return (
        <div>
            <br/>
            <h3>Comments:</h3>
            {commentsList}
        </div>
    )
}

export default Comments