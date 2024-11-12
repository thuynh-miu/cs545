import { useState } from 'react'
import Posts from '../components/Posts'
import PostModel from '../model/post'

const Dashboard = () => {
    const [posts, setPosts] = useState(PostModel.getAll())
    const [currentPost, setCurrentPost] = useState(null)
    const [title, setTitle] = useState('')

    const handleChangeName = (e) => {
        e.preventDefault()
        const updatedPost = PostModel.updateById(currentPost.id, title)
        if (updatedPost) {
            setPosts(posts.map(post => 
                post.id === updatedPost.id ? updatedPost : post
            ))
            setCurrentPost(updatedPost)
        }
    }

    const handleKeyDown = (e) => {
        if (e.key === 'Enter') {
            handleChangeName(e)
        }
    }

    const handlePostClick = (id) => {
        const p = PostModel.getById(id)
        if (p) {
            setCurrentPost(p)
            setTitle(p.title)
        }
    }

    return (
        <div style={styles.dashboard}>
            <Posts posts={posts} onClick={handlePostClick}/>

            {/* Change Name */}
            <form style={styles.form} onSubmit={handleChangeName}>
                <label style={styles.label}>Update Title: </label>
                <input
                    style={styles.input}
                    type="text"
                    placeholder="title"
                    value={title}
                    onChange={(e) => {
                        setTitle(e.target.value)
                    }}
                    onKeyDown={handleKeyDown}
                />
                <button style={styles.button} type="submit">{'Change Name'}</button>
            </form>

            {/* Post Details */}
            <div style={styles.postDetails}>
                <div style={styles.postDetailsTitle}>
                    <p style={{ textDecoration: 'underline' }}>{currentPost.title}</p>
                    <p>{currentPost.author}</p>
                </div>
                <div style={styles.postDetailsContent}>
                    <p>This is the content in the post…</p>
                </div>
                <div style={styles.postDetailsButton}>
                    <button style={styles.buttonUnderline} type="submit">{'Edit'}</button>
                    <button style={styles.buttonUnderline} type="submit">{'Delete'}</button>
                </div>
            </div>
        </div>
    )
}

const styles = {
    dashboard: {
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
    },

    form: {
        marginTop: '20px',
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'start',
        justifyContent: 'center',
        padding: '8px 25px',
        backgroundColor: '#f5f5f5',
        boxShadow: '0 4px 8px rgba(0, 0, 0, 0.1)',
        width: '600px'
    },
    label: {
        fontSize: '18px',
        fontWeight: 'bold',
        color: '#333',
        marginRight: '10px',
    },
    input: {
        marginTop: '10px',
        padding: '5px',
        width: '40%',
        height: '30px',
        borderRadius: '5px',
        border: '1px solid #ccc',
        fontSize: '16px'
    },
    button: {
        width: '160px',
        height: '40px',
        padding: '5px 10px',
        borderRadius: '5px',
        backgroundColor: '#4CAF50',
        color: '#fff',
        border: 'none',
        fontSize: '16px',
        cursor: 'pointer',
        transition: 'background-color 0.3s ease',
        marginTop: '10px'
    },

    postDetails: {
        marginTop: '20px',
        width: '600px',
        border: '2px solid #385d8a',
        padding: '10px',
    },
    postDetailsTitle: {
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
    },
    postDetailsContent: {
        display: 'flex',
        flexDirection: 'column'
    },
    postDetailsButton: {
        display: 'flex',
        justifyContent: 'center',
        marginTop: '40px'
    },
    buttonUnderline: {
        border: 'none',
        backgroundColor: 'transparent',
        textDecoration: 'underline',
        marginRight: '10px',
        fontSize: '16px',
        color: 'red',
        cursor: 'pointer',
        margin: '5px 20px'
    }
}

export default Dashboard;