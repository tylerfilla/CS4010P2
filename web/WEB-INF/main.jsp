<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Math Question Bank</title>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/mathjax/2.7.4/MathJax.js?config=TeX-MML-AM_CHTML"></script>
    <script type="text/javascript">
        MathJax.Hub.Config({
            tex2jax: {
                inlineMath: [
                    ["$", "$"],
                    ["\\(", "\\)"]
                ]
            }
        });
    </script>
</head>
<body>
<h2>Math Question Bank</h2>
<p>Welcome to the math question bank! You may browse the stored problems, manage their categorization, or submit your
    own problems to the bank.</p>
<h3>Math Problems</h3>
<p>The following is a list of all math problems that match your criteria.</p>
<div style="text-align: center;">
    <form method="get">
        <label for="cat-filter">Filter by category:</label><br/>
        <select id="cat-filter" name="cat" class="form-control" >
            <option value="0">No filter</option>
            <%--@elvariable id="categories" type="java.util.List"--%>
            <c:forEach items="${categories}" var="cat">
                <option value="${cat.cid}">${cat.name}</option>
            </c:forEach>
        </select>
        <button type="submit" class="btn btn-default">Filter</button>
    </form>
    <div class="btn-group">
        <button class="btn btn-default" data-toggle="modal" data-target="#compose-dialog">
            <span class="glyphicon glyphicon-pencil"></span> Compose problem
        </button>
        <button class="btn btn-default" data-toggle="modal" data-target="#category-dialog">
            <span class="glyphicon glyphicon-plus"></span> Add category
        </button>
    </div>
</div>
<table class="table table-striped table-hover">
    <thead>
    <tr>
        <td>ID</td>
        <td>Content</td>
        <td>Action</td>
    </tr>
    </thead>
    <tbody>
    <%--@elvariable id="problems" type="java.util.List"--%>
    <c:forEach items="${problems}" var="prob">
        <tr>
            <td>${prob.pid}</td>
            <td>
                <div>${prob.content}</div>
                <div>
                    <b>Categories:</b>
                        <%--@elvariable id="catmap" type="java.util.Map"--%>
                    <c:if test="${empty catmap[prob.pid]}">
                        <i>none</i>
                    </c:if>
                    <c:if test="${not empty catmap[prob.pid]}">
                        <c:forEach items="${catmap[prob.pid]}" var="cat">
                            ${cat.name}&nbsp;
                        </c:forEach>
                    </c:if>
                </div>
            </td>
            <td>
                <form action="assign" method="get">
                    <label for="problem-${prob.pid}-cats">Categories:</label><br/>
                    <select id="problem-${prob.pid}-cats" name="cid" class="form-control">
                        <c:forEach items="${categories}" var="cat">
                            <option value="${cat.cid}">${cat.name}</option>
                        </c:forEach>
                    </select><br/>
                    <button type="submit" class="btn btn-default">
                        <span class="glyphicons glyphicons-ok"></span> Assign
                    </button>
                    <input type="hidden" name="pid" value="${prob.pid}"/>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div id="compose-dialog" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form action="compose" method="post">
                <div class="modal-header">
                    <h5 class="modal-title">Compose Problem</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <label for="compose-content">Problem Content</label><br/>
                    <textarea id="compose-content" name="content" class="form-control"></textarea>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">
                        <span class="glyphicon glyphicon-ok"></span> Save
                    </button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">
                        <span class="glyphicon glyphicon-remove"></span> Discard
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
<div id="category-dialog" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <form action="category" method="get">
                <div class="modal-header">
                    <h5 class="modal-title">Add Category</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <label for="category-dialog-name">Name:</label><br/>
                    <input type="text" id="category-dialog-name" name="name" class="form-control"/>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary">
                        <span class="glyphicon glyphicon-ok"></span> Save
                    </button>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">
                        <span class="glyphicon glyphicon-remove"></span> Discard
                    </button>
                </div>
                <input type="hidden" name="action" value="create"/>
            </form>
        </div>
    </div>
</div>
</body>
</html>
