import Post from './Post'

const Posts = (props) => {

    const postsList = props.posts.map(post => {
        return (
            <Post
                key={post.id} // Add key prop here
                id={post.id}
                title={post.title}
                author={post.author}
                deletePost = {() => props.deletePost(post.id)}
                setSelected={() => props.setSelected(post.id)}
            />
        )
    })

    return (
        <div style={styles.container}>
            {postsList}
        </div>
    )
}

const styles = {
    container: {
        display: 'flex',
        justifyContent: 'center',
        width: '600px'
    }
}

export default Posts;